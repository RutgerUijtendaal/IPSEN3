package api.resources;

import database.daos.ParentDao;
import database.models.Parent;

import javax.ws.rs.Path;

@Path("/parent")
public class ParentResource extends GenericResource<Parent> {

    protected ParentResource(ParentDao dao) {
        super(dao);
    }

}
