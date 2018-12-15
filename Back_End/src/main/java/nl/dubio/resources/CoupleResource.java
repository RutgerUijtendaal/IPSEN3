package nl.dubio.resources;

import nl.dubio.models.Couple;
import nl.dubio.models.CoupleListModel;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.models.Parent;
import nl.dubio.service.CoupleService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
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
                System.currentTimeMillis()
        );
    }

    @GET
    @Path("/all-list")
    public List<CoupleListModel> getAllCoupleList() {
        List<CoupleListModel> allCouples = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
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
