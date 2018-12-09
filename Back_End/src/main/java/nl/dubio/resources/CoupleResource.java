package nl.dubio.resources;

import nl.dubio.models.Couple;
import nl.dubio.models.CoupleRegistry;
import nl.dubio.service.CoupleService;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

}
