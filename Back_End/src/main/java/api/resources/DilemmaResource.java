package api.resources;

import database.daos.DilemmaDao;
import database.models.Dilemma;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/dilemma")
@Produces(MediaType.APPLICATION_JSON)
public class DilemmaResource extends GenericResource<Dilemma> {

    protected DilemmaResource(DilemmaDao dao) {
        super(dao);
    }
}
