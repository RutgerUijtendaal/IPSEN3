package api.resources;

import database.daos.ChildDao;
import database.models.Child;

import javax.ws.rs.Path;

@Path("/child")
public class ChildResource extends GenericResource<Child> {

    protected ChildResource(ChildDao dao) {
        super(dao);
    }
}
