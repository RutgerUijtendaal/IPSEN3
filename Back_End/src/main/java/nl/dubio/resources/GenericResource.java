package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.DatabaseObject;
import io.dropwizard.setup.Environment;
import nl.dubio.models.Parent;
import nl.dubio.service.CrudService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Produces(MediaType.APPLICATION_JSON)
public abstract class GenericResource<T extends DatabaseObject<T>> {

    protected final CrudService<T> crudService;

    protected GenericResource(CrudService<T> crudService) {
        this.crudService = crudService;
    }

    @GET
    @Timed
    public List<T> getAll(@Auth Optional<Authorizable> authorizable){
        checkAuthentication(authorizable, "getAll");
        return crudService.getAll();
    }

    @GET
    @Timed
    @Path("/{id}")
    public T getById(@Auth Optional<Authorizable> authorizable, @PathParam("id") Integer id){
        checkAuthentication(authorizable, "getID");
        return crudService.getById(id);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer save(@Auth Optional<Authorizable> authorizable, @Valid T object) throws InvalidInputException {
        checkAuthentication(authorizable, "insert");
        return crudService.save(object);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public boolean update(@Auth Optional<Authorizable> authorizable, @Valid T object) throws InvalidInputException {
        checkAuthentication(authorizable, "update");
        return crudService.update(object);
    }

    @DELETE
    @Timed
    public boolean delete(@Auth Optional<Authorizable> authorizable, @Valid T object){
        checkAuthentication(authorizable, "delete");
        return crudService.delete(object);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deleteById(@Auth Optional<Authorizable> authorizable, @PathParam("id") Integer id){
        checkAuthentication(authorizable, "deleteId");
        return crudService.deleteById(id);
    }

    protected abstract void checkAuthentication(Optional<Authorizable> authorizable, String request);

    public static void initResources(Environment environment){

        environment.jersey().register(new AdminResource());
        environment.jersey().register(new AnswerResource());
        environment.jersey().register(new ChildResource());
        environment.jersey().register(new CoupleResource());
        environment.jersey().register(new DilemmaResource());
        environment.jersey().register(new ParentResource());
        environment.jersey().register(new ResultResource());
        environment.jersey().register(new RightResource());
        environment.jersey().register(new LoginResource());

        // Also create and register the resource for a specific view in the database
        environment.jersey().register(new CoupleListViewResource());
    }

}
