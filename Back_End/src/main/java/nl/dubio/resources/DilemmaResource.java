package nl.dubio.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.auth.Auth;
import nl.dubio.auth.Authorizable;
import nl.dubio.exceptions.ClientException;
import nl.dubio.exceptions.InvalidAnswerException;
import nl.dubio.exceptions.InvalidInputException;
import nl.dubio.models.Couple;
import nl.dubio.models.Admin;
import nl.dubio.models.Dilemma;
import nl.dubio.models.databag.AnswerDilemmaDatabag;
import nl.dubio.service.CoupleService;
import nl.dubio.service.DilemmaService;
import nl.dubio.service.ParentService;
import nl.dubio.service.ResultService;

import javax.validation.Valid;
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
    @Path("/answer/{token}")
    @Produces(MediaType.APPLICATION_JSON)
    public AnswerDilemmaDatabag getDilemmaForParent(@PathParam("token") String token) throws ClientException {
        AnswerDilemmaDatabag databag = ((DilemmaService)crudService).getByParentToken(token);
        return databag;
    }

    @POST
    @Path("/answer/{token}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void saveDilemmaAnswers(@PathParam("token") String token, @FormParam("answerId") int answerId) throws ClientException {
        if (!((DilemmaService)crudService).validAnswer(token, answerId)) {
            throw new InvalidAnswerException();
        } else {
            ResultService service = new ResultService();
            service.updateResult(token, answerId);
        }
    }

    @GET
    @Path("/token-generate")
    public void tokenGenerator() throws InvalidInputException {
        CoupleService coupleService = new CoupleService();
        ParentService parentService = new ParentService();
        List<Couple> couples = coupleService.getAll();

        for (Couple couple : couples) {
            coupleService.createResultEntry(couple);
        }
    }

    @GET
    @Timed
    @Path("/{period}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public List<Dilemma> getAllPeriod(@PathParam("period") String period){
        List<Dilemma> dilemmas = crudService.getAll();
        return dilemmas.stream().filter(dilemma -> {
            if (dilemma.getPeriod() == null) {
                return false;
            }
            return dilemma.getPeriod().equalsIgnoreCase(period);
        })
                .collect(Collectors.toList());
    }

    @PUT
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
    //TODO Roles Allowed
    public boolean update(@Auth Admin admin, @Valid Dilemma object){
        try {
            return crudService.update(object);
        } catch (InvalidInputException e) {
            e.printStackTrace();
        }
        return false;
    }

    @GET
    @Timed
    //TODO ROLES ALLOWED
    public List<Dilemma> getAll(@Auth Authorizable authorizable){
        return crudService.getAll();
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public Integer save(@Auth Admin admin, @Valid Dilemma object) throws InvalidInputException {
        return crudService.save(object);
    }

    @DELETE
    @Timed
    @Path("/{id}")
    public boolean deleteById(@Auth Optional<Authorizable> authorizable, @PathParam("id") Integer id){
        return crudService.deleteById(id);
    }


}
