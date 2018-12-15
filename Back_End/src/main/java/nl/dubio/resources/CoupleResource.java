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
        allCouples.add(
                new CoupleListModel(
                        5,
                        new Parent(1, "jordi", "+3156234234", "jordi@gmail.com"),
                        new Parent(2, "alwkd", "+3156232344", "alwkd@gmail.com")
                )
        );
        return allCouples;
    }
}
