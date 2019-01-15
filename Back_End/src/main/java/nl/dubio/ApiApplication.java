package nl.dubio;

import com.google.common.collect.Lists;
import io.dropwizard.Application;
import io.dropwizard.auth.*;
import io.dropwizard.auth.basic.BasicCredentialAuthFilter;
import io.dropwizard.auth.chained.ChainedAuthFilter;
import io.dropwizard.setup.Environment;
import nl.dubio.auth.*;
import nl.dubio.config.ApiConfiguration;
import nl.dubio.factories.PreparedStatementFactory;
import nl.dubio.models.Admin;
import nl.dubio.models.Parent;
import nl.dubio.resources.FileUploadResource;
import nl.dubio.resources.GenericResource;
import nl.dubio.utils.MailUtility;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.security.Principal;
import java.util.ArrayList;
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

        setupAuthentication(environment);

        GenericResource.initResources(environment);

        mailUtility = configuration.getMailUtility();
    }

    private void setupAuthentication(Environment environment) {
        AuthFilter authFilterAdmin = new BasicCredentialAuthFilter.Builder<Admin>()
                        .setAuthenticator(new AdminAuthenticator())
                        .setAuthorizer(new AdminAuthorizer())
                        .setRealm("ADMIN")
                        .buildAuthFilter();
        AuthFilter authFilterParent = new BasicCredentialAuthFilter.Builder<Parent>()
                        .setAuthenticator(new ParentAuthenticator())
                        .setAuthorizer(new ParentAuthorizer())
                        .setRealm("PARENT")
                        .buildAuthFilter();
        ArrayList<AuthFilter> filters = Lists.newArrayList(authFilterAdmin, authFilterParent);
        environment.jersey().register(new AuthDynamicFeature(new ChainedAuthFilter(filters)));
        environment.jersey().register(RolesAllowedDynamicFeature.class);
        environment.jersey().register(new AuthValueFactoryProvider.Binder<>(Authorizable.class));
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

        // Setup image uploading
        environment.jersey().register(MultiPartFeature.class);
        environment.jersey().register(FileUploadResource.class);
    }


}
