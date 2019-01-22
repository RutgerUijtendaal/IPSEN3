package nl.dubio.resources;

import nl.dubio.auth.Authorizable;
import nl.dubio.models.Child;
import nl.dubio.service.ChildService;

import javax.ws.rs.Path;
import java.util.Optional;

@Path("/child")
public class ChildResource extends GenericResource<Child> {

    protected ChildResource() {
        super(new ChildService());
    }

    @Override
    protected void checkAuthentication(Optional<Authorizable> authorizable, String request) {

    }
}
