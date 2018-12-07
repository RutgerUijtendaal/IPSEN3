package api.resources;

import database.daos.RightDao;
import database.models.Right;

import javax.ws.rs.Path;

@Path("/right")
public class RightResource extends GenericResource<Right> {

    protected RightResource(RightDao dao) {
        super(dao);
    }

}
