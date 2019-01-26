package nl.dubio.persistance;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.*;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.DatabaseObject;
import org.hibernate.validator.constraints.NotEmpty;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * super class of all generic DAOs
 * a generic DAO represents a table from the database
 *
 * all standard CRUD operations are defined here
 *
 * this class also provides commonly used methods for executing and closing statements
 *
 * to get information about it's subclasses,
 * abstract methods are defined so they can be called from their
 * super class (this class)
 *
 * @author Bas de Bruyn
 * @param <T> class of the model the crudService interacts with
 */
public abstract class GenericDao<T extends DatabaseObject<T>>{

    public final static String idColumnName = "id";

    private final GenericDao<T> daoSubclass;

    @NotEmpty
    @JsonProperty
    protected final String tableName;

    @NotEmpty
    @JsonProperty
    protected final String[] columnNames;

    @JsonCreator
    public GenericDao(String tableName, String[] columnNames) {
        this.tableName = tableName;
        this.columnNames = columnNames;

        daoSubclass = getDao();
    }

    public List<T> getAll() {
        PreparedStatement preparedStatement = PreparedStatementFactory.createSelectAllStatement(daoSubclass.getTableName());

        return executeGetAll(preparedStatement);
    }

    public T getById(int id) {
        PreparedStatement statement = PreparedStatementFactory.createSelectByIdStatement(daoSubclass.getTableName(), id);

        return executeGetByAttribute(statement);
    }

    public boolean idExists(Integer id){
        PreparedStatement statement =
                PreparedStatementFactory.createExistsByAttributeStatement(tableName, idColumnName);

        fillParameter(statement, 1, id);

        return executeIsTrue(statement);
    }

    /**
     * @return the id the database automatically generated for the object
     */
    public int save(T savedObject) {
        int generatedKey;
        PreparedStatement statement = PreparedStatementFactory.createInsertStatement(daoSubclass.getTableName(), daoSubclass.getColumnNames());

        daoSubclass.fillPreparedStatement(statement, savedObject);
        execute(statement);

        try{
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                generatedKey = resultSet.getInt(1);
                resultSet.close();
            } else {
                throw new NoFurtherResultsException();
            }
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        } finally {
            closeTransaction(statement);
        }

        return generatedKey;
    }

    /**
     * @return if the object was successfully updated
     */
    public boolean update(T updatedObject) {
        PreparedStatement statement = PreparedStatementFactory.createUpdateStatement(daoSubclass.getColumnNames(), daoSubclass.getTableName(), updatedObject.getId());

        daoSubclass.fillPreparedStatement(statement, updatedObject);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;
    }

    /**
     * @return if the object was successfully deleted
     */
    public boolean deleteById(int deletedObjectId) {
        PreparedStatement statement = PreparedStatementFactory.createDeleteStatement(daoSubclass.getTableName(), deletedObjectId);

        boolean successful = executeUpdate(statement);

        closeTransaction(statement);

        return successful;
    }

    /**
     * @return if the object was successfully deleted
     */
    public boolean delete(T deletedObject) {
        return deleteById(deletedObject.getId());
    }

    public static ResultSet executeQuery(PreparedStatement preparedStatement){
        try {
            return preparedStatement.executeQuery();
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new ExecutePreparedStatementException();
        }
    }

    public static void execute(PreparedStatement preparedStatement){
        try {
            System.out.println("!!!!" + preparedStatement.toString());
            preparedStatement.execute();
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new ExecutePreparedStatementException();
        }
    }

    public static boolean executeUpdate(PreparedStatement preparedStatement){
        try {
            System.out.println("!!!!" + preparedStatement.toString());
            return preparedStatement.executeUpdate() == 1;
        } catch (SQLException exception){
            exception.printStackTrace();
            throw new ExecutePreparedStatementException();
        }
    }

    /**
     * executes a statement that returns if a condition is true
     */
     public static boolean executeIsTrue(PreparedStatement statement){
        boolean isTrue;

        ResultSet resultSet = executeQuery(statement);

        try {
            if(resultSet.next()) {
                isTrue = resultSet.getBoolean(1);
            } else {
                throw new NoFurtherResultsException();
            }
            resultSet.close();
        } catch (SQLException exception) {
            throw new ReadFromResultSetException();
        } finally {
            closeTransaction(statement);
        }

        return isTrue;
    }

    /**
     * executes a statement that returns an object
     * that has a certain value for an attribute
     */
    public T executeGetByAttribute(PreparedStatement statement){
        T object;

        ResultSet resultSet = executeQuery(statement);

        try {
            if(resultSet.next()) {
                object = daoSubclass.createFromResultSet(resultSet);
            } else {
                object = null;
            }
            resultSet.close();
        } catch (SQLException exception) {
            throw new ReadFromResultSetException();
        } finally {
            closeTransaction(statement);
        }

        return object;
    }

    public List<T> executeGetAll(PreparedStatement statement){
        List<T> objects = new ArrayList<>();

        ResultSet resultSet = executeQuery(statement);

        try{
            while (resultSet.next()) {
                objects.add(daoSubclass.createFromResultSet(resultSet));
            }
            resultSet.close();
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        } finally {
            closeTransaction(statement);
        }

        if (objects.size() == 0){
            return null;
        }

        return objects;
    }

    public static void closeTransaction(PreparedStatement statement){
        try{
            Connection connection = statement.getConnection();
            statement.close();
            connection.close();
        } catch (SQLException exception){
            throw new CloseDatabaseConnectionException();
        }
    }

    public static void fillParameter(PreparedStatement statement, int index, String value){
        try {
            statement.setString(index, value);
        } catch (SQLException exception) {
            throw new FillPreparedStatementException();
        }
    }

    public static void fillParameter(PreparedStatement statement, int index, int value){
        try {
            statement.setInt(index, value);
        } catch (SQLException exception) {
            throw new FillPreparedStatementException();
        }
    }

    public static void fillParameter(PreparedStatement statement, int index, Date value) {
        try {
            statement.setDate(index, value);
        } catch (SQLException exception) {
            throw new FillPreparedStatementException();
        }
    }

    public static void fillParameter(PreparedStatement statement, int index, short value){
        try {
            statement.setShort(index, value);
        } catch (SQLException exception) {
            throw new FillPreparedStatementException();
        }
    }

    public static void fillParameter(PreparedStatement statement, int index, boolean value){
        try {
            statement.setBoolean(index, value);
        } catch (SQLException exception) {
            throw new FillPreparedStatementException();
        }
    }

    protected abstract T createFromResultSet(ResultSet resultSet);

    protected abstract void fillPreparedStatement(PreparedStatement preparedStatement, T object);

    protected abstract GenericDao<T> getDao();

    public String getTableName() { return tableName; }

    public String[] getColumnNames() { return columnNames; }

}
