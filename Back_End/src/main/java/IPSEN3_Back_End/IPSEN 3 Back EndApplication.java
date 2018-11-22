package IPSEN3_Back_End;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class IPSEN 3 Back EndApplication extends Application<IPSEN 3 Back EndConfiguration> {

    public static void main(final String[] args) throws Exception {
        new IPSEN 3 Back EndApplication().run(args);
    }

    @Override
    public String getName() {
        return "IPSEN 3 Back End";
    }

    @Override
    public void initialize(final Bootstrap<IPSEN 3 Back EndConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final IPSEN 3 Back EndConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
    }

}
