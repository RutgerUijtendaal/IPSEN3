package api.resources;

import database.daos.DilemmaDao;
import database.models.Dilemma;

import javax.ws.rs.Path;

@Path("/dilemma")
public class DilemmaResource extends GenericResource<Dilemma> {

    protected DilemmaResource(DilemmaDao dao) {
        super(dao);
    }
}
