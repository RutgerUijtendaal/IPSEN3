package nl.dubio.factories;

import nl.dubio.exceptions.CreateStatementException;
import nl.dubio.exceptions.PrepareStatementException;
import nl.dubio.persistance.GenericDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author Bas de Bruyn
 */
public class PreparedStatementFactory {

    private static ConnectionFactory connectionFactory;

    public static void setConnectionFactory(ConnectionFactory connectionFactory) {
        PreparedStatementFactory.connectionFactory = connectionFactory;
    }

    public static Statement createStatement(){
        Connection connection = createConnection();

        try {
            return connection.createStatement();
        } catch (SQLException exception){
            throw new CreateStatementException();
        }
    }

    public static PreparedStatement createPreparedStatement(String query){
        Connection connection = createConnection();

        try {
            return connection.prepareStatement(query);
        } catch (SQLException exception){
            throw new PrepareStatementException();
        }
    }

    public static PreparedStatement createPreparedStatementWithReturnedKey(String query){
        Connection connection = createConnection();

        try {
            return connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
        } catch (SQLException exception){
            throw new PrepareStatementException();
        }
    }

    public static PreparedStatement createSelectAllStatement(String tableName){
        String query = "SELECT * FROM " + tableName + ";";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createSelectByIdStatement(String tableName, int id){
        String query = "SELECT * FROM " + tableName + " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, 1, id);

        return statement;
    }

    public static PreparedStatement createSelectByAttributeStatement(String tableName, String columnName){
        String query = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?;";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createSelectByAttributesStatement(String table, String[] columnNames){
        String query = "SELECT * FROM " + table + " WHERE ";

        query += String.join(" = ? AND ", columnNames) + " = ?;";

        return createPreparedStatement(query);
    }

    public static PreparedStatement createSelectAttributeStatement(String tableName, String columnName){
        String query = "SELECT " + columnName + " FROM " + tableName;

        return createPreparedStatement(query);
    }

    public static PreparedStatement createInsertStatement(String tableName, String[] columnNames){
        String query = "INSERT INTO " + tableName + "(";

        query += String.join(", ", columnNames);

        query += ") VALUES(?";

        query = appendValue(query, ",?",columnNames.length -1);

        query += ");";

        return createPreparedStatementWithReturnedKey(query);
    }

    public static PreparedStatement createUpdateStatement(String[] columnNames, String tableName, int id){
        String query = "UPDATE " + tableName;

        query += " SET " + String.join(" = ? , ", columnNames) + " = ?";

        query += " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, columnNames.length + 1, id);

        return statement;
    }

    public static PreparedStatement createDeleteStatement(String tableName, int id){
        String query = "DELETE FROM " + tableName + " WHERE id = ?;";

        PreparedStatement statement = createPreparedStatement(query);

        GenericDao.fillParameter(statement, 1, id);

        return statement;
    }
    
    public static PreparedStatement createExistsByAttributeStatement(String tableName, String columnName){
        String query = "SELECT (COUNT(*) >= 1)\n" +
                "FROM " + tableName + "\n" +
                "WHERE " + columnName + " = ?;";

        return PreparedStatementFactory.createPreparedStatement(query);
    }

    private static String appendValue(String string, String value, int times){
        StringBuilder queryBuilder = new StringBuilder(string);
        for (int i = 1; i <= times; i++) {
            queryBuilder.append(value);
        }
        return queryBuilder.toString();
    }

    public static Connection createConnection() {
        return connectionFactory.createConnection();
    }
}
