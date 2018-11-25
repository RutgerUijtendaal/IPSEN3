package api.resources;

import database.daos.ResultDao;
import database.models.Result;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/result")
@Produces(MediaType.APPLICATION_JSON)
public class ResultResource extends GenericResource<Result> {

    protected ResultResource(ResultDao dao) {
        super(dao);
    }

}
