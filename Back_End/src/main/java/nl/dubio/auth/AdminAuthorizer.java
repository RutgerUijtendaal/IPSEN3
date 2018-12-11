package nl.dubio.auth;

import io.dropwizard.auth.Authorizer;
import nl.dubio.models.Admin;

public class AdminAuthorizer implements Authorizer<Admin> {

    @Override
    public boolean authorize(Admin admin, String s) {
        return admin != null;
    }

}
