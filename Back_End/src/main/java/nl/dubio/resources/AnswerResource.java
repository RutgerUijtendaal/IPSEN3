package nl.dubio.resources;

import nl.dubio.auth.Authorizable;
import nl.dubio.models.Answer;
import nl.dubio.service.AnswerService;

import javax.ws.rs.Path;
import java.util.Optional;

@Path("/answer")
public class AnswerResource extends GenericResource<Answer> {

    public AnswerResource() {
        super(new AnswerService());
    }

    @Override
    protected void checkAuthentication(Optional<Authorizable> authorizable, String request) {

    }
}
