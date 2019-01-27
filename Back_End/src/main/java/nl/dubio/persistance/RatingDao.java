package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.models.Rating;

import java.sql.*;

public class RatingDao extends GenericDao<Rating> {

    @JsonCreator
    public RatingDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    @Override
    public Rating createFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            int dilemma_id = resultSet.getInt(columnNames[0]);
            int rating = resultSet.getInt(columnNames[1]);
            Timestamp date = resultSet.getTimestamp(columnNames[2]);
            return new Rating(id, dilemma_id, rating, date);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Rating rating) {
        try {
            preparedStatement.setInt(1, rating.getDilemmaId());
            preparedStatement.setInt(2, rating.getRating());
            preparedStatement.setTimestamp(3, rating.getDate());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Rating> getDao() { return this; }
}
