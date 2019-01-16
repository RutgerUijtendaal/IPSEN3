package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Dilemma;
import nl.dubio.exceptions.FillPreparedStatementException;
import nl.dubio.exceptions.ReadFromResultSetException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class DilemmaDao extends GenericDao<Dilemma> {

    @JsonCreator
    public DilemmaDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    public Dilemma getByWeekNr(int week) {
        PreparedStatement statement = PreparedStatementFactory.createSelectByAttributeStatement(tableName, columnNames[0]);

        fillParameter(statement, 1, week);

        return executeGetByAttribute(statement);
    }

    public boolean dilemmaExists(Short weekNr) {

        String query = "SELECT (COUNT(" + columnNames[0] + ") >= 1)\n" +
                "FROM " + tableName + "\n" +
                "WHERE  " + columnNames[0] + " = ?;";

        PreparedStatement statement = PreparedStatementFactory.createPreparedStatement(query);

        fillParameter(statement,1, weekNr);

        return executeIsTrue(statement);
    }

    @Override
    public Dilemma createFromResultSet(ResultSet resultSet) {
        try {
            int id = resultSet.getInt("id");
            short week_nr = resultSet.getShort(columnNames[0]);
            String theme = resultSet.getString(columnNames[1]);
            String feedback = resultSet.getString(columnNames[2]);
            String periode = resultSet.getString(columnNames[3]);
            return new Dilemma(id, week_nr, theme, feedback, periode);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Dilemma dilemma){
        try {
            preparedStatement.setShort(1, dilemma.getWeekNr());
            preparedStatement.setString(2, dilemma.getTheme());
            preparedStatement.setString(3, dilemma.getFeedback());
            preparedStatement.setString(4, dilemma.getPeriode());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Dilemma> getDao() { return this; }
}

