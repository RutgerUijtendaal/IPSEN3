package nl.dubio.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import nl.dubio.factories.ConnectionFactory;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.utils.MailUtility;

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

    @Valid
    @NotNull
    @JsonProperty("mailUtility")
    private MailUtility mailUtility;

    public DaoRepository getDaoRepository() { return daoRepository; }
    public void setDaoRepository(DaoRepository daoRepository) { this.daoRepository = daoRepository; }
    public ConnectionFactory getConnectionFactory() { return connectionFactory; }
    public void setConnectionFactory(ConnectionFactory connectionFactory) { this.connectionFactory = connectionFactory; }
    public MailUtility getMailUtility() { return this.mailUtility; }
    public void setMailUtility(MailUtility mailUtility) { this.mailUtility = mailUtility; }
}
