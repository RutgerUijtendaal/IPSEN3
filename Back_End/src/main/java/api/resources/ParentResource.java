package api.resources;

import database.daos.ParentDao;
import database.models.Parent;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/parent")
@Produces(MediaType.APPLICATION_JSON)
public class ParentResource extends GenericResource<Parent> {

    protected ParentResource(ParentDao dao) {
        super(dao);
    }

}
