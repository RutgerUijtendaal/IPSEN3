package nl.dubio.auth;

import io.dropwizard.auth.Authorizer;
import nl.dubio.models.Parent;

public class ParentAuthorizer implements Authorizer<Parent> {
    @Override
    public boolean authorize(Parent parent, String s) {
        return false;
    }
}
