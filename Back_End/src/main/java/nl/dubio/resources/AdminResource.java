package nl.dubio.resources;

import nl.dubio.models.Admin;
import nl.dubio.service.AdminService;

import javax.ws.rs.Path;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource() {
        super(new AdminService());
    }

}
