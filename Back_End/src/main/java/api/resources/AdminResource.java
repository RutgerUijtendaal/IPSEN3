package api.resources;

import database.daos.AdminDao;
import database.models.Admin;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource extends GenericResource<Admin> {

    public AdminResource(AdminDao dao) {
        super(dao);
    }

}
