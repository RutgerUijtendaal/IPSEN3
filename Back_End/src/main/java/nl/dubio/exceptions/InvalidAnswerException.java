package nl.dubio.exceptions;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

public class InvalidAnswerException extends ClientErrorException {
    public InvalidAnswerException() {
        super("Invalid answer", Response.Status.OK);
    }
}
