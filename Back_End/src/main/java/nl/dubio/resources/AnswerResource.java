package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Admin;
import nl.dubio.models.Answer;
import nl.dubio.service.AnswerService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/answer")
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource() {
        super(new AnswerService());
    }

    @GET
    @Timed
    public List<Answer> getAll(Authorizable authorizable){
        return crudService.getAll();
    }


    @GET
    @Timed
    @Path("/{id}")
    public Answer getById(Authorizable authorizable, @PathParam("id") Integer id){
        return crudService.getById(id);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    //TODO Roles Allowed
    public boolean update(@Auth Admin admin, @Valid Answer object) throws InvalidInputException {
        return crudService.update(object);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer save(@Auth Optional<Authorizable> authorizable, @Valid Answer object) throws InvalidInputException {
        return crudService.save(object);
    }
}
