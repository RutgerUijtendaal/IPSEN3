package database.daos;

import database.models.Admin;
import exceptions.FillPreparedStatementException;
import exceptions.ReadFromResultSetException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class AdminDao extends GenericDao<Admin> {

//    private final String tableName = "admin";
//    private final String[] columnNames= {
//            "email",
//            "password",
//            "rights_id"
//    };

    private final String tableName;
    private final String[] columnNames;


    public AdminDao(String tableName, String[] columnNames) {
        this.tableName = tableName;
        this.columnNames = columnNames;
    }


    public void updateWithoutPassword(Admin admin) {
        final String[] columnNamesWithoutPassword = {
            columnNames[0],
            columnNames[2]
        };
        String query = "UPDATE admin SET " + columnNamesWithoutPassword[0] + " = ? , " + columnNamesWithoutPassword[1] + " = ? WHERE id = ?;" ;

        PreparedStatement state = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(state, 1, admin.getEmail());
        fillParameter(state, 2, admin.getRights_id());
        fillParameter(state, 3, admin.getId());

        boolean successfull = executeUpdate(state);

        closeTransaction(state);

    }

    public Admin getByEmail(String email) {
        String query = "SELECT * FROM " + tableName + "\n" +
                "WHERE " + columnNames[0] + " LIKE ?;";

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement, 1, "%" + email + "%");

        return executeGetByAttribute(statement);
    }

    public boolean emailExists(String admin_email) {
        String query = "SELECT (COUNT(" + columnNames[0] + ") >= 1)\n" +
                "FROM " + tableName + "\n" +
                "WHERE " + columnNames[0] + " = ?;";

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement, 1, admin_email);

        return executeIsTrue(statement);
    }

    @Override
    public Admin createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            String email = resultSet.getString(columnNames[0]);
            String password = resultSet.getString(columnNames[1]);
            int rights_id = resultSet.getInt(columnNames[2]);
            return new Admin(id, email, password, rights_id);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Admin admin){
        try {
            preparedStatement.setString(1, admin.getEmail());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setInt(3, admin.getRights_id());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public String getTableName() {
        return tableName;
    }

    @Override
    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public GenericDao<Admin> getDao() {
        return this;
    }
}

