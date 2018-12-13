package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import nl.dubio.auth.AdminRights;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Admin;
import nl.dubio.service.AdminService;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.security.Principal;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource() {
        super(new AdminService());
    }

    @GET
    @Path("/test")
    @RolesAllowed(AdminRights.Constants.DILEMMAS)
    @Produces(MediaType.TEXT_PLAIN)
    public String getSecretPlan(@Auth Authorizable authorizable) {
        if (authorizable instanceof Admin) {
            Admin admin = (Admin) authorizable;
            return admin.getEmail();
        }
        throw new NotAuthorizedException("");
    }

}
