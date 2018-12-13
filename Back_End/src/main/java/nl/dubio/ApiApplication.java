package nl.dubio;

import io.dropwizard.Application;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.setup.Environment;
import nl.dubio.auth.AdminAuthenticator;
import nl.dubio.auth.AdminAuthorizer;
import nl.dubio.config.ApiConfiguration;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Admin;
import nl.dubio.resources.GenericResource;
import nl.dubio.utils.MailUtility;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.mail.MessagingException;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class ApiApplication extends Application<ApiConfiguration> {

    private static MailUtility mailUtility;

    public static void main(String[] args) throws Exception {
        new ApiApplication().run(args);
    }

    @Override
    public void run(ApiConfiguration configuration, Environment environment){
        setupCORS(environment);

        PreparedStatementFactory.setConnectionFactory(configuration.getConnectionFactory());

        environment.jersey().register(new AuthDynamicFeature(
                new BasicCredentialAuthFilter.Builder<Admin>()
                        .setAuthenticator(new AdminAuthenticator())
                        .setAuthorizer(new AdminAuthorizer())
                        .setRealm("SUPER SECRET STUFF")
                        .buildAuthFilter()));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        //If you want to use @Auth to inject a custom Principal type into your resource
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Admin.class));

        GenericResource.initResources(environment);

        mailUtility = configuration.getMailUtility();
    }

    public static MailUtility getMailUtility() { return mailUtility; }

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
