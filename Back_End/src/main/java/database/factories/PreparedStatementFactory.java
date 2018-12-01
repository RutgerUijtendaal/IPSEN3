package database.factories;

import database.daos.GenericDao;
import exceptions.PrepareStatementException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class PreparedStatementFactory {

    private static ConnectionFactory connectionFactory;

    public static void setConnectionFactory(ConnectionFactory connectionFactory) {
        PreparedStatementFactory.connectionFactory = connectionFactory;
    }



    public static PreparedStatement createPreparedStatement(String query){
        Connection connection = connectionFactory.getConnection();

        try {
            return connection.prepareStatement(query);
        } catch (SQLException exception){
            throw new PrepareStatementException();
        }
    }

    public static PreparedStatement createPreparedStatementWithReturnedKey(String query){
        Connection connection = connectionFactory.getConnection();

        try {
            return connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception){
            throw new PrepareStatementException();
        }
    }

    public static PreparedStatement createSelectAllStatement(String table){
        String query = "SELECT * FROM " + table + ";";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createSelectByIdStatement(String table, int id){
        String query = "SELECT * FROM " + table + " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, 1, id);

        return statement;
    }

    public static PreparedStatement createSelectByAttributeStatement(String table, String attribute){
        String query = "SELECT * FROM " + table + " WHERE " + attribute + " = ?;";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createInsertStatement(String table, String[] columnNames){
        StringBuilder query = new StringBuilder("INSERT INTO " + table + "(" + columnNames[0]);

        for (int i = 1; i < columnNames.length; i++) {
            query.append(",").append(columnNames[i]);
        }

        query.append(")" + " VALUES(?");

        for (int i = 1; i < columnNames.length; i++) {
            query.append(",?");
        }

        query.append(");");

        return createPreparedStatementWithReturnedKey(query.toString());
    }

    public static PreparedStatement createUpdateStatement(String[] columnNames, String table, int id){
        StringBuilder query = new StringBuilder("UPDATE " + table);

        query.append(" SET ").append(columnNames[0]).append(" = ?");

        for (int i = 1; i < columnNames.length; i++) {
            query.append(" , ").append(columnNames[i]).append(" = ?");
        }

        query.append(" WHERE id = ?;");

        PreparedStatement statement = createPreparedStatement(query.toString());

        GenericDao.fillParameter(statement, columnNames.length + 1, id);

        return statement;
    }

    public static PreparedStatement createDeleteStatement(String table, int id){
        String query = "DELETE FROM " + table + " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, 1, id);

        return statement;
    }

}
