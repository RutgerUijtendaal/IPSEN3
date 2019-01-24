package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.models.Admin;
import nl.dubio.service.AdminService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.List;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource() {
        super(new AdminService());
    }

    @GET
    @Timed
    //TODO Roles Allowed
    public List<Admin> getAll(@Auth Admin admin){
        return crudService.getAll();
    }

}
