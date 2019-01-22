package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Answer;
import nl.dubio.models.Parent;
import nl.dubio.persistance.ResultDao;
import nl.dubio.models.Result;
import nl.dubio.service.ResultService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/result")
public class ResultResource extends GenericResource<Result> {

    protected ResultResource() {
        super(new ResultService());
    }

    @GET
    @Path("/parent/{parentId}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Result> getResultsByParent(@Auth Authorizable authorizable, @PathParam("parentId") IntParam parentId) {
        return ((ResultService) crudService).getByParent(parentId.get());
    }

}
