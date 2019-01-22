package nl.dubio.resources;

import nl.dubio.auth.Authorizable;
import nl.dubio.persistance.RightDao;
import nl.dubio.models.Right;
import nl.dubio.service.RightService;

import javax.ws.rs.Path;
import java.util.Optional;

@Path("/right")
public class RightResource extends GenericResource<Right> {

    protected RightResource() {
        super(new RightService());
    }

    @Override
    protected void checkAuthentication(Optional<Authorizable> authorizable, String request) {

    }
}
