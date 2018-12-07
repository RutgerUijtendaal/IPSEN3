package api.resources;

import database.daos.ResultDao;
import database.models.Result;

import javax.ws.rs.Path;

@Path("/result")
public class ResultResource extends GenericResource<Result> {

    protected ResultResource(ResultDao dao) {
        super(dao);
    }

}
