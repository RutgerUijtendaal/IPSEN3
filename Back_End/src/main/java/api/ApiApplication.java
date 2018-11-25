package api;

import api.config.ApiConfiguration;
import api.resources.GenericResource;
import database.factories.PreparedStatementFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ApiApplication extends Application<ApiConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }



    @Override
    public void run(ApiConfiguration configuration, Environment environment){
        setupCORS(environment);

        PreparedStatementFactory.setConnectionFactory(configuration.getConnectionFactory());

        GenericResource.initResources(configuration, environment);
    }



    private void setupCORS(Environment environment) {
        final FilterRegistration.Dynamic cors =
                environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin,Authorization");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }


}
