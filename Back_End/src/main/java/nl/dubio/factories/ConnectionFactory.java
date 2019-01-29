package nl.dubio.factories;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.exceptions.OpenDatabaseConnectionException;
import nl.dubio.exceptions.RegisterDriverException;
import org.hibernate.validator.constraints.NotEmpty;
import org.postgresql.Driver;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Bas de Bruyn
 */
public class ConnectionFactory {

    @NotEmpty
    @JsonProperty
    private final String JDBC;

    @NotEmpty
    @JsonProperty
    private final String HOST;

    @NotNull
    @JsonProperty
    private final Integer PORT;

    @NotEmpty
    @JsonProperty
    private final String DATABASE;

    @NotEmpty
    @JsonProperty
    private final String USER;

    @NotEmpty
    @JsonProperty
    private final String PASSWORD;

    private final String databaseUrl;

    @JsonCreator
    public ConnectionFactory(
        @JsonProperty("jdbc") String jdbc,
        @JsonProperty("host") String host,
        @JsonProperty("port") Integer port,
        @JsonProperty("database") String database,
        @JsonProperty("user") String user,
        @JsonProperty("password") String password
    ) {
        JDBC = jdbc;
        HOST = host;
        PORT = port;
        DATABASE = database;
        USER = user;
        PASSWORD = password;
        databaseUrl = String.format("jdbc:%s://%s:%d/%s", JDBC, HOST, PORT, DATABASE);
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException e) {
            throw new RegisterDriverException();
        }
    }

    public Connection createConnection() {
        try {
            return DriverManager.getConnection(databaseUrl, USER, PASSWORD);
        } catch (SQLException exception) {
            throw new OpenDatabaseConnectionException();
        }
    }
}
