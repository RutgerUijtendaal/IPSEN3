package database.daos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import database.models.Right;
import exceptions.FillPreparedStatementException;
import exceptions.ReadFromResultSetException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class RightDao extends GenericDao<Right> {

    @JsonCreator
    public RightDao(@JsonProperty("tableName") String tableName, @JsonProperty("columnNames") String[] columnNames) {
        super(tableName, columnNames);
    }

    @Override
    public Right createFromResultSet(ResultSet resultSet){
        try {
            int id = resultSet.getInt("id");
            boolean can_edit_dilemma = resultSet.getBoolean(columnNames[0]);
            boolean can_view_statistics = resultSet.getBoolean(columnNames[1]);
            boolean can_edit_user_info = resultSet.getBoolean(columnNames[2]);
            return new Right(id, can_edit_dilemma, can_view_statistics, can_edit_user_info);
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }

    @Override
    public void fillPreparedStatement(PreparedStatement preparedStatement, Right right){
        try {
            preparedStatement.setBoolean(1, right.isCanEditDilemma());
            preparedStatement.setBoolean(2, right.isCanViewStatistics());
            preparedStatement.setBoolean(3, right.isCanEditUserInfo());
        } catch (SQLException exception){
            throw new FillPreparedStatementException();
        }
    }

    @Override
    public GenericDao<Right> getDao() { return this; }
}
