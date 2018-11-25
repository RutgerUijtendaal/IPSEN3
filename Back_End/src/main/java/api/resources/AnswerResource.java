package api.resources;

import database.daos.AnswerDao;
import database.models.Answer;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/answer")
@Produces(MediaType.APPLICATION_JSON)
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource(AnswerDao dao) {
        super(dao);
    }

}
