package api.resources;

import database.daos.RightDao;
import database.models.Right;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/right")
@Produces(MediaType.APPLICATION_JSON)
public class RightResource extends GenericResource<Right> {

    protected RightResource(RightDao dao) {
        super(dao);
    }

}
