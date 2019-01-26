package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.NoFurtherResultsException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Admin;

import java.sql.*;

/**
 * @author Bas de Bruyn
 */
public class AdminDao extends GenericDao<Admin> {

    @JsonCreator
    public AdminDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public boolean updateWithoutPassword(Admin admin) {
        String query = "UPDATE admin SET " + columnNames[0] + " = ? , " + columnNames[2] + " = ? WHERE id = ?;" ;

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement, 1, admin.getEmail());
        fillParameter(statement, 2, admin.getRightId());
        fillParameter(statement, 3, admin.getId());

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;
    }

    private boolean removeToken(String token) {

        String query = "UPDATE admin SET " + columnNames[4] + " = null WHERE " + columnNames[4] + " = ?";
        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);
        fillParameter(statement, 1, token);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;

    }

    public boolean updatePassword(String token, String hashedPassword) {

        String query = "UPDATE admin SET " + columnNames[1] + " = ? WHERE " + columnNames[4] + " = ?";
        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);
        fillParameter(statement, 1, hashedPassword);
        fillParameter(statement, 2, token);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        if (!successful) {
            return false;
        }

        return removeToken(token);
    }

    public Admin getByEmail(String email) {
        String query = "SELECT * FROM " + tableName + "\n" +
                "WHERE " + columnNames[0] + " LIKE ?;";

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement, 1, "%" + email + "%");

        return executeGetByAttribute(statement);
    }

    public boolean emailExists(String admin_email) {
        PreparedStatement statement =
                PreparedStatementFactory.createExistsByAttributeStatement(tableName, columnNames[0]);

        fillParameter(statement, 1, admin_email);

        return executeIsTrue(statement);
    }

    @Override
    public Admin createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            String email = resultSet.getString(columnNames[0]);
            String password = resultSet.getString(columnNames[1]);
            int rightsId = resultSet.getInt(columnNames[2]);
            Date signupDate = resultSet.getDate(columnNames[3]);
            return new Admin(id, email, password, rightsId, signupDate, null);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Admin admin){
        try {
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getRightId());
            preparedStatement.setDate(4, new Date(System.currentTimeMillis()));
            preparedStatement.setString(5, null);
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Admin> getDao() { return this; }
}

