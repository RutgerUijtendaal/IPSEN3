package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Child;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.models.Parent;
import nl.dubio.service.PasswordService;
import nl.dubio.utils.TokenGenerator;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class CoupleDao extends GenericDao<Couple> {

    @JsonCreator
    public CoupleDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public Couple getByParent(Parent parent) {
        if (parent == null) {
            return null;
        }

        String query = "SELECT * FROM " + tableName + " WHERE " + columnNames[0] + " = ? OR " + columnNames[1] + " = ?;";
        PreparedStatement preparedStatement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(preparedStatement, 1, parent.getId());
        fillParameter(preparedStatement, 2, parent.getId());

        return executeGetByAttribute(preparedStatement);
    }

    public boolean tokenExists(String token) {
        PreparedStatement statement = PreparedStatementFactory.createExistsByAttributeStatement(tableName, columnNames[5]);
        fillParameter(statement, 1, token);
        return executeIsTrue(statement);
    }

    private boolean removeToken(String token) {

        String query = "UPDATE couple SET " + columnNames[5] + " = null WHERE " + columnNames[5] + " = ?";
        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);
        fillParameter(statement, 1, token);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;

    }

    public boolean updatePassword(String token, String hashedPassword) {

        String query = "UPDATE couple SET " + columnNames[3] + " = ? WHERE " + columnNames[5] + " = ?";
        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);
        fillParameter(statement, 2, token);
        fillParameter(statement, 1, hashedPassword);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        if (!successful) {
            return false;
        }

        return removeToken(token);
    }

    public Couple getByToken(String token) {
        String query = "SELECT * FROM " + tableName + " WHERE " + columnNames[4] + " = ?;";

        PreparedStatement preparedStatement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(preparedStatement, 1, token);

        return executeGetByAttribute(preparedStatement);

    }

    public String resetPasswordRequest(Couple couple) {

        String passwordToken = TokenGenerator.getToken();

        String query = "UPDATE couple SET " + columnNames[5] + " = ? WHERE id = ?;";

        PreparedStatement preparedStatement = PreparedStatementFactory.createPreparedStatement(query);
        fillParameter(preparedStatement, 1, passwordToken);
        fillParameter(preparedStatement, 2, couple.getId());

        executeUpdate(preparedStatement);

        return passwordToken;
    }

    public int saveCoupleViaRegistry(CoupleRegistry registry) throws InvalidKeySpecException, NoSuchAlgorithmException {
        ParentDao parentDao = DaoRepository.getParentDao();
        ChildDao childDao = DaoRepository.getChildDao();

        Parent parent1 = new Parent(registry.getFirstName1(),
                registry.getEmail1(),
                registry.getPhoneNr1());
        int parent1Id = parentDao.save(parent1);

        Parent parent2 = new Parent(registry.getFirstName2(),
                registry.getEmail2(),
                registry.getPhoneNr2());
        int parent2Id = parentDao.save(parent2);

        Couple couple = new Couple(new Date(System.currentTimeMillis()), parent1Id, parent2Id, PasswordService.generatePasswordHash(registry.getPassword()), registry.getToken(), null);
        int coupleId = save(couple);

        Child child = new Child(coupleId, registry.getDate(), registry.getIsBorn());
        childDao.save(child);


        return coupleId;
    }

    @Override
    public Couple createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            Date signup_date = resultSet.getDate("signup_date");
            int parent1_id = resultSet.getInt("parent1_id");
            int parent2_id = resultSet.getInt("parent2_id");
            String password = resultSet.getString("password");
            String token = resultSet.getString("token");
            String passwordToken = resultSet.getString("password_token");
            return new Couple(id, signup_date, parent1_id, parent2_id, password, token, passwordToken);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Couple couple){
        try {
            preparedStatement.setInt(1, couple.getParent1Id());
            preparedStatement.setInt(2, couple.getParent2Id());
            preparedStatement.setDate(3, couple.getSignupDate());
            preparedStatement.setString(4, couple.getPassword());
            preparedStatement.setString(5, couple.getToken());
            preparedStatement.setString(6, couple.getPasswordToken());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Couple> getDao() { return this; }
}

