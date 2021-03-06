package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Parent;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class ParentDao extends GenericDao<Parent> {

    @JsonCreator
    public ParentDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public boolean emailExists(String parent_email) {
        PreparedStatement statement =
                PreparedStatementFactory.createExistsByAttributeStatement(tableName, columnNames[1]);

        fillParameter(statement, 1, parent_email);

        return executeIsTrue(statement);
    }

    public Parent getByEmail(String email) {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[1]);

        fillParameter(preparedStatement,1, email);

        return executeGetByAttribute(preparedStatement);
    }

    public Parent getByToken(String token) {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[3]);

        fillParameter(preparedStatement,1, token);

        return executeGetByAttribute(preparedStatement);
    }

    @Override
    public Parent createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            String first_name = resultSet.getString(columnNames[0]);
            String email = resultSet.getString(columnNames[1]);
            String phone_number = resultSet.getString(columnNames[2]);
            String token = resultSet.getString(columnNames[3]);

            return new Parent(id, first_name, phone_number, email, token);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Parent parent){
        try {
            preparedStatement.setString(1, parent.getFirstName());
            preparedStatement.setString(2, parent.getEmail());
            preparedStatement.setString(3, parent.getPhoneNr());
            preparedStatement.setString(4, parent.getToken());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Parent> getDao() { return this; }
}

