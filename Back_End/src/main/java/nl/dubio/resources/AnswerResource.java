package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonView;
import io.dropwizard.auth.Auth;
import nl.dubio.View;
import nl.dubio.auth.AdminRights;
import nl.dubio.auth.Authorizable;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.models.Answer;
import nl.dubio.service.AnswerService;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/answer")
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource() {
        super(new AnswerService());
    }

    @GET
    @Timed
    @JsonView(View.Public.class)
    public List<Answer> getAll(Authorizable authorizable){
        return crudService.getAll();
    }


    @GET
    @Timed
    @Path("/{id}")
    @JsonView(View.Public.class)
    public Answer getById(Authorizable authorizable, @PathParam("id") Integer id){
        return crudService.getById(id);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    @RolesAllowed(AdminRights.Constants.DILEMMAS)
    public boolean update(@Auth Admin admin, @Valid Answer object) throws InvalidInputException {
        return crudService.update(object);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed(AdminRights.Constants.DILEMMAS)
    public Integer save(@Auth Authorizable authorizable, @Valid Answer object) throws InvalidInputException {
        return crudService.save(object);
    }
}
