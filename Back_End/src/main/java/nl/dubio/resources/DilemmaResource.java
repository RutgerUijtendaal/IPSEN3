package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import nl.dubio.exceptions.InvalidAnswerException;
import nl.dubio.models.Couple;
import nl.dubio.models.Dilemma;
import nl.dubio.models.Parent;
import nl.dubio.models.Result;
import nl.dubio.models.databag.AnswerDilemmaDatabag;
import nl.dubio.service.CoupleService;
import nl.dubio.service.DilemmaService;
import nl.dubio.service.ParentService;
import nl.dubio.service.ResultService;
import nl.dubio.utils.TokenGenerator;

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
    @Path("/answer/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerDilemmaDatabag getDilemmaForParent(@PathParam("token") String token) {
        AnswerDilemmaDatabag databag = ((DilemmaService)crudService).getByParentToken(token);
        return databag;
    }

    @POST
    @Path("/answer/{token}")
    public void saveDilemmaAnswers(@PathParam("token") String token, @QueryParam("answerId") int answerId) {
        if (!((DilemmaService)crudService).validAnswer(token, answerId)) {
            throw new InvalidAnswerException();
        } else {
            ResultService service = new ResultService();
            service.updateResult(token, answerId);
        }
    }

    @GET
    @Path("/token-generate")
    public void tokenGenerator() {
        CoupleService coupleService = new CoupleService();
        ParentService parentService = new ParentService();
        List<Couple> couples = coupleService.getAll();

        for (Couple couple : couples) {
            coupleService.createResultEntry(couple);
        }
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
