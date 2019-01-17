package nl.dubio.resources;

import nl.dubio.models.Answer;
import nl.dubio.models.Parent;
import nl.dubio.persistance.ResultDao;
import nl.dubio.models.Result;
import nl.dubio.service.ResultService;

import javax.ws.rs.Path;

@Path("/result")
public class ResultResource extends GenericResource<Result> {

    protected ResultResource() {
        super(new ResultService());
    }

}
