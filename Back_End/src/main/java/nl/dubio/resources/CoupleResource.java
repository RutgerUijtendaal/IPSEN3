package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Admin;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.models.Parent;
import nl.dubio.service.CoupleService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/couple")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleResource extends GenericResource<Couple> {

    protected CoupleResource() {
        super(new CoupleService());
    }

    @GET
    @Path("/email")
    public Couple getByEmail(@Auth Authorizable authorizable) {
        if (authorizable instanceof Parent) {
            String email = ((Parent) authorizable).getEmail();
            return ((CoupleService) crudService).getCoupleByEmail(email);
        } else {
            throw new NotAuthorizedException("");
        }
    }

    @POST
    @Path("/register")
    public int register(@Valid CoupleRegistry couple) throws InvalidInputException {
        return ((CoupleService) crudService).register(couple);
    }

    @PUT
    @Timed
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/password/{token}")
    public boolean updatePassword(@PathParam("token") String token,
                                  String password) {
        if (token.length() != 32) {
            return false;
        }

        try {
            new CoupleService().updatePassword(token, password);
        } catch (InvalidInputException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @DELETE
    @Path("/unregister")
    public Response unregister(@QueryParam("token") String token) {
        try {
            ((CoupleService) crudService).unregister(token);
        } catch (NotFoundException e) {
            throw e;
        }
        return Response.ok().build();
    }

    @DELETE
    @Timed
    @Path("/{id}")
    //TODO ROLES ALLOWED
    public boolean deleteById(@Auth Admin admin, @PathParam("id") Integer id){
        return crudService.deleteById(id);
    }



}
