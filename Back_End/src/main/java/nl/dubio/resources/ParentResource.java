package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Parent;
import nl.dubio.service.ParentDataService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.Optional;

@Path("/parent")
public class ParentResource extends GenericResource<Parent> {

    protected ParentResource() {
        super(new ParentDataService());
    }


    @GET
    @Timed
    @Path("/{id}")
    //TODO ROLES ALLOWED
    public Parent getById(@Auth Optional<Authorizable> authorizable, @PathParam("id") Integer id){
        return crudService.getById(id);
    }

}
