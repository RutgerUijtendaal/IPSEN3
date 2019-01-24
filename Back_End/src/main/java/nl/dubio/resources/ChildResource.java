package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import nl.dubio.auth.Authorizable;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Child;
import nl.dubio.service.ChildService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/child")
public class ChildResource extends GenericResource<Child> {

    protected ChildResource() {
        super(new ChildService());
    }

    @GET
    @Path("/couple/{coupleId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Child getChildByCouple(@Auth Authorizable authorizable, @PathParam("coupleId")IntParam coupleId) {
        return ((ChildService) crudService).getByCouple(coupleId.get());
    }

    @Override
    protected void checkAuthentication(Optional<Authorizable> authorizable, String request) {

    }

}
