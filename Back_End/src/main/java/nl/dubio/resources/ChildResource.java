package nl.dubio.resources;

import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import nl.dubio.View;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Child;
import nl.dubio.models.Couple;
import nl.dubio.service.ChildService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/child")
public class ChildResource extends GenericResource<Child> {

    protected ChildResource() {
        super(new ChildService());
    }

    @GET
    @Path("/couple/{coupleId}")
    @Produces(MediaType.APPLICATION_JSON)
    @JsonView(View.Public.class)
    public Child getChildByCouple(@Auth Authorizable authorizable, @PathParam("coupleId")IntParam coupleId) {
        return ((ChildService) crudService).getByCouple(coupleId.get());
    }

    @POST
    @Path("/born/{childId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Child setChildBorn(@Auth Couple couple, @PathParam("childId")IntParam childId) {
        if (couple.getId() == crudService.getById(childId.get()).getCoupleId()) {
            return ((ChildService) crudService).setChildBorn(childId.get());
        }
        throw new NotAuthorizedException("");
    }

}
