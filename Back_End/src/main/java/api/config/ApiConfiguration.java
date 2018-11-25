package api.config;

import api.factories.DaoRepositoryFactory;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ApiConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty("daoRepository")
    private DaoRepositoryFactory daoRepository;


    public DaoRepositoryFactory getDaoRepository() {
        return daoRepository;
    }

    public void setDaoRepository(DaoRepositoryFactory daoRepository) {
        this.daoRepository = daoRepository;
    }
}
