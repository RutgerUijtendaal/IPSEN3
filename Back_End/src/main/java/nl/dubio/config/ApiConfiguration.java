package nl.dubio.config;

import nl.dubio.persistance.DaoRepository;
import com.fasterxml.jackson.annotation.JsonProperty;
import nl.dubio.factories.ConnectionFactory;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("daoRepository")
    private DaoRepository daoRepository;

    @Valid
    @NotNull
    @JsonProperty("databaseConnection")
    private ConnectionFactory connectionFactory;

    public DaoRepository getDaoRepository() { return daoRepository; }
    public void setDaoRepository(DaoRepository daoRepository) { this.daoRepository = daoRepository; }
    public ConnectionFactory getConnectionFactory() { return connectionFactory; }
    public void setConnectionFactory(ConnectionFactory connectionFactory) { this.connectionFactory = connectionFactory; }
}
