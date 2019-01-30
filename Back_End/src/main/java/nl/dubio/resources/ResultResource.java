package nl.dubio.resources;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.IntParam;
import nl.dubio.models.Couple;
import nl.dubio.models.Parent;
import nl.dubio.models.Result;
import nl.dubio.persistance.CoupleDao;
import nl.dubio.persistance.DaoRepository;
import nl.dubio.service.ResultService;

import javax.ws.rs.*;
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
    public List<Result> getResultsByParent(@Auth Parent parent, @PathParam("parentId") IntParam parentId) {
        return ((ResultService) crudService).getByParent(parentId.get());
    }
}
