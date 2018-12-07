package api.resources;

import database.daos.AnswerDao;
import database.models.Answer;

import javax.ws.rs.Path;

@Path("/answer")
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource(AnswerDao dao) {
        super(dao);
    }

}
