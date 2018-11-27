package database.daos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.factories.PreparedStatementFactory;
import database.models.Couple;
import database.models.Parent;
import exceptions.FillPreparedStatementException;
import exceptions.ReadFromResultSetException;

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
        String query = "SELECT * FROM " + tableName + " WHERE " + columnNames[0] + " = ? OR " + columnNames[1] + " = ?;";
        PreparedStatement preparedStatement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(preparedStatement, 1, parent.getId());
        fillParameter(preparedStatement, 2, parent.getId());

        return executeGetByAttribute(preparedStatement);
    }



    @Override
    public Couple createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            Date signup_date = resultSet.getDate("signup_date");
            int parent1_id = resultSet.getInt("parent1_id");
            int parent2_id = resultSet.getInt("parent2_id");
            return new Couple(id, signup_date, parent1_id, parent2_id);
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
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }



    @Override
    public GenericDao<Couple> getDao() { return this; }
}

