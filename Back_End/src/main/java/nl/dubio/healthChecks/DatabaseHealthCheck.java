package nl.dubio.healthChecks;

import com.codahale.metrics.health.HealthCheck;
import nl.dubio.exceptions.ReadFromResultSetException;
import nl.dubio.factories.PreparedStatementFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHealthCheck extends HealthCheck {

    @Override
    protected Result check() throws Exception {
        String query = "SELECT 1;";

        Statement statement = PreparedStatementFactory.createStatement();

        try {
            ResultSet resultSet = statement.executeQuery(query);
            if (resultSet.next() && resultSet.getInt(1) == 1)
                return Result.healthy();
            else
                return Result.unhealthy("Can't Select 1 from database");
        } catch (SQLException exception){
            throw new ReadFromResultSetException();
        }
    }
}
