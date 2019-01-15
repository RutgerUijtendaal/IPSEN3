package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.models.Dilemma;
import nl.dubio.service.DilemmaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Path("/dilemma")
public class DilemmaResource extends GenericResource<Dilemma> {

    protected DilemmaResource() {
        super(new DilemmaService());
    }

    @GET
    @Timed
    @Path("/{periode}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Dilemma> getAllPeriode(@PathParam("periode") String periode){
        List<Dilemma> dilemmas = crudService.getAll();
        return dilemmas.stream().filter(dilemma -> {
            if (dilemma.getPeriode() == null) {
                return false;
            }
            return dilemma.getPeriode().equalsIgnoreCase(periode);
        })
                .collect(Collectors.toList());
    }
}
