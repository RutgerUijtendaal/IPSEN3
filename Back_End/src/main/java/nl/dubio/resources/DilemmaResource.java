package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.auth.Authorizable;
import nl.dubio.models.Dilemma;
import nl.dubio.service.DilemmaService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/dilemma")
public class DilemmaResource extends GenericResource<Dilemma> {

    protected DilemmaResource() {
        super(new DilemmaService());
    }

    @GET
    @Timed
    @Path("/{period}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Dilemma> getAllperiod(@PathParam("period") String period){
        List<Dilemma> dilemmas = crudService.getAll();
        return dilemmas.stream().filter(dilemma -> {
            if (dilemma.getPeriod() == null) {
                return false;
            }
            return dilemma.getPeriod().equalsIgnoreCase(period);
        })
                .collect(Collectors.toList());
    }

    @Override
    protected void checkAuthentication(Optional<Authorizable> authorizable, String request) {

    }
}
