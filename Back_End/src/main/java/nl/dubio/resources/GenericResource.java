package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.DatabaseObject;
import io.dropwizard.setup.Environment;
import nl.dubio.models.Parent;
import nl.dubio.service.CrudService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Produces(MediaType.APPLICATION_JSON)
public abstract class GenericResource<T extends DatabaseObject<T>> {

    protected final CrudService<T> crudService;

    protected GenericResource(CrudService<T> crudService) {
        this.crudService = crudService;
    }

    @GET
    @Timed
    public List<T> getAll(){
        return crudService.getAll();
    }

    @GET
    @Timed
    @Path("/{id}")
    public T getById(@PathParam("id") Integer id){
        return crudService.getById(id);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer save(@Valid @NotNull T object) throws InvalidInputException {
        return crudService.save(object);
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public boolean update(@Valid @NotNull T object) throws InvalidInputException {
        return crudService.update(object);
    }

    @DELETE
    @Timed
    public boolean delete(@Valid @NotNull T object){
        return crudService.delete(object);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deleteById(@PathParam("id") Integer id){
        return crudService.deleteById(id);
    }

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
