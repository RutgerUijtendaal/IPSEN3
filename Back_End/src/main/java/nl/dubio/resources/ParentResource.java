package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Admin;
import nl.dubio.models.Parent;
import nl.dubio.service.ParentService;

import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/parent")
public class ParentResource extends GenericResource<Parent> {

    protected ParentResource() {
        super(new ParentService());
    }


    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String parentLoginTest(@Auth Authorizable authorizable) {
        if (authorizable instanceof Parent) {
            Parent parent = (Parent) authorizable;
            return parent.getEmail();
        }
        throw new NotAuthorizedException("");
    }

}
