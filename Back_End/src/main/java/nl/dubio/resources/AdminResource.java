package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.models.Admin;
import nl.dubio.service.AdminService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource() {
        super(new AdminService());
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSecretPlan(@Auth Admin admin) {
        return admin.getEmail();
    }

}
