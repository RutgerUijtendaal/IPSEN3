package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Child;
import nl.dubio.models.Config;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigDao extends GenericDao<Config> {
    @JsonCreator
    public ConfigDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public Config getByName(String name) {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[0]);

        fillParameter(preparedStatement, 1, name);

        return executeGetByAttribute(preparedStatement);
    }

    @Override
    protected Config createFromResultSet(ResultSet resultSet) {
        try {
            String name = resultSet.getString(columnNames[0]);
            String value = resultSet.getString(columnNames[1]);;

            return new Config(name, value);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    protected void fillPreparedStatement(PreparedStatement preparedStatement, Config object) {
        try {
            preparedStatement.setString(1, object.getName());
            preparedStatement.setString(2, object.getValue());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    protected GenericDao<Config> getDao() {
        return this;
    }

    /**
     * @return if the object was successfully updated
     */
    public boolean update(Config updatedObject) {
        PreparedStatement statement = PreparedStatementFactory.createUpdateStatement(this.getColumnNames(), this.getTableName(), updatedObject.getName());

        this.fillPreparedStatement(statement, updatedObject);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;
    }
}
