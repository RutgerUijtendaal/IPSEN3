package nl.dubio.resources;

import nl.dubio.models.Couple;
import nl.dubio.models.CoupleListModel;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.models.Parent;
import nl.dubio.service.CoupleService;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/couple")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleResource extends GenericResource<Couple> {

    protected CoupleResource() {
        super(new CoupleService());
    }

    @POST
    @Path("/register")
    public int register(@Valid CoupleRegistry couple){
        return ((CoupleService) crudService).register(couple);
    }

    @DELETE
    @Path("/unregister")
    public Response unregister(@QueryParam("token") String token) {
        try {
            ((CoupleService) crudService).unregister(token);
        } catch (NotFoundException e) {
            throw e;
        }
        return Response.ok().build();
    }

    @GET
    @Path("/getRegistry")
    public CoupleRegistry getRegistry(){
        return new CoupleRegistry(
                "John",
                "06-65984348",
                "JohnDoe@foo.com",
                "Jane",
                "06-13487621",
                "JaneDoe@bar.com",
                true,
                System.currentTimeMillis(),
                "Test"
        );
    }

    @GET
    @Path("/all-list")
    public List<CoupleListModel> getAllCoupleList() {
        List<CoupleListModel> allCouples = new ArrayList<>();
        for (int i = 1; i < 100; i += 2) {
            allCouples.add(
                    new CoupleListModel(
                            i,
                            new Parent(i, "user" + i, "+3156234234", i + "user@gmail.com"),
                            new Parent(i+1, "user" + i+1, "+3156232344", i+1 + "user@gmail.com")
                    )
            );
        }
        return allCouples;
    }
}
