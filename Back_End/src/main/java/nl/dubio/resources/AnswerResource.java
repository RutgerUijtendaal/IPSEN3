package nl.dubio.resources;

import nl.dubio.models.Answer;
import nl.dubio.service.AnswerService;

import javax.ws.rs.Path;

@Path("/answer")
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource() {
        super(new AnswerService());
    }

}
