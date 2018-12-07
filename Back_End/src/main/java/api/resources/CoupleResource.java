package api.resources;

import database.daos.CoupleDao;
import database.models.Couple;
import database.models.CoupleRegistry;

import javax.validation.Valid;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/couple")
@Produces(MediaType.APPLICATION_JSON)
public class CoupleResource extends GenericResource<Couple> {

    protected CoupleResource(CoupleDao dao) {
        super(dao);
    }

    @POST
    @Path("/register")
    public boolean register(@Valid CoupleRegistry couple){
        return true;
    }

}
