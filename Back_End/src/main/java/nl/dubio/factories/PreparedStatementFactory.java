package nl.dubio.factories;

import nl.dubio.persistance.GenericDao;
import nl.dubio.exceptions.PrepareStatementException;

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

    public static PreparedStatement createSelectByAttributeStatement(String table, String[] attributes){
        StringBuilder stringBuilder = new StringBuilder();

        for (String attribute : attributes) {
            stringBuilder.append(attribute + " = ? AND ");
        }

        String attributeString = stringBuilder.toString();
        attributeString = attributeString.substring(0, attributeString.lastIndexOf("AND") - 1);

        String query = "SELECT * FROM " + table + " WHERE " + attributeString + ";";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createInsertStatement(String table, String[] columnNames){
        String query = "INSERT INTO " + table + "(";

        query += String.join(", ", columnNames);

        query += ") VALUES(?";

        query = appendValue(query, ",?",columnNames.length -1);

        query += ");";

        return createPreparedStatementWithReturnedKey(query);
    }

    public static PreparedStatement createUpdateStatement(String[] columnNames, String table, int id){
        String query = "UPDATE " + table;

        query += " SET " + String.join(" = ? , ", columnNames) + " = ?";

        query += " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, columnNames.length + 1, id);

        return statement;
    }

    public static PreparedStatement createDeleteStatement(String table, int id){
        String query = "DELETE FROM " + table + " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, 1, id);

        return statement;
    }

    private static String appendValue(String string, String value, int times){
        StringBuilder queryBuilder = new StringBuilder(string);
        for (int i = 1; i <= times; i++) {
            queryBuilder.append(value);
        }
        return queryBuilder.toString();
    }

}
