package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Result;

import java.sql.*;
import java.util.List;

/**
 * @author Bas de Bruyn
 */
public class ResultDao extends GenericDao<Result> {

    @JsonCreator
    public ResultDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public boolean isDilemmaAnswered(int parentId) {
        // Query to check if the most recent dilemma has been answered
        String subQuery = "SELECT * FROM " + tableName + " WHERE " + columnNames[0] + " = ? ORDER BY id DESC LIMIT 1";

        String query = "SELECT (COUNT(" + columnNames[0] + ") >= 1)\n" +
                "FROM (" + subQuery + ") AS result\n" +
                "WHERE " + columnNames[3] + " IS NOT NULL;";

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement,1, parentId);

        return executeIsTrue(statement);
    }

    public List<Result> getByParentId(int id) {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[0]);

        fillParameter(preparedStatement,1, id);

        return executeGetAll(preparedStatement);
    }

    public Result getRecentByParentId(int id) {
        // Query to check if the most recent dilemma has been answered
        String query = "SELECT * FROM " + tableName + " WHERE " + columnNames[0] + " = ? ORDER BY id DESC LIMIT 1";
        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement,1, id);
        return executeGetByAttribute(statement);
    }

    @Override
    public Result createFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int parent_id = resultSet.getInt(columnNames[0]);
            int answer_id = resultSet.getInt(columnNames[1]);
            Timestamp date_dilemma_sent = resultSet.getTimestamp(columnNames[2]);
            Timestamp date_dilemma_answered = resultSet.getTimestamp(columnNames[3]);
            return new Result(id, parent_id, answer_id, date_dilemma_sent, date_dilemma_answered);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Result result) {
        try {
            preparedStatement.setInt(1, result.getParentId());
            if(result.getAnswerId() == null) {
                preparedStatement.setNull(2, Types.INTEGER);
            } else {
                preparedStatement.setInt(2, result.getAnswerId());
            }
            preparedStatement.setTimestamp(3, result.getSentTime());
            preparedStatement.setTimestamp(4, result.getAnsweredTime());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Result> getDao() { return this; }
}
