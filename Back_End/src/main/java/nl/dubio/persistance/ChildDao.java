package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Child;
import nl.dubio.models.Couple;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class ChildDao extends GenericDao<Child> {

    @JsonCreator
    public ChildDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public Child getByCouple(Couple couple) {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[0]);

        fillParameter(preparedStatement, 1, couple.getId());

        return executeGetByAttribute(preparedStatement);
    }

    @Override
    public Child createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            int couple_id = resultSet.getInt(columnNames[0]);
            boolean is_born = resultSet.getBoolean(columnNames[1]);
            Date date = resultSet.getDate(columnNames[2]);

            return new Child(id, couple_id, date, is_born);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Child child){
        try {
            preparedStatement.setInt(1, child.getCoupleId());
            preparedStatement.setBoolean(2, child.getIsBorn());
            preparedStatement.setDate(3, child.getDate());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Child> getDao() { return this; }
}

