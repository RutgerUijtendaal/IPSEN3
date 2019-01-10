package nl.dubio.resources;

import nl.dubio.persistance.DilemmaDao;
import nl.dubio.models.Dilemma;
import nl.dubio.service.DilemmaService;

import javax.ws.rs.Path;

@Path("/dilemma")
public class DilemmaResource extends GenericResource<Dilemma> {

    protected DilemmaResource() {
        super(new DilemmaService());
    }
}
