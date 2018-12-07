package api.resources;

import database.daos.AdminDao;
import database.models.Admin;

import javax.ws.rs.Path;

@Path("/admin")
public class AdminResource extends GenericResource<Admin> {

    public AdminResource(AdminDao dao) {
        super(dao);
    }

}
