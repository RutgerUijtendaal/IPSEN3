package nl.dubio.resources;

import io.dropwizard.setup.Environment;
import nl.dubio.models.DatabaseObject;
import nl.dubio.service.CrudService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Produces(MediaType.APPLICATION_JSON)
public abstract class GenericResource<T extends DatabaseObject<T>> {

    protected final CrudService<T> crudService;

    protected GenericResource(CrudService<T> crudService) {
        this.crudService = crudService;
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
        environment.jersey().register(new PasswordResource());
    }

}
