package nl.dubio.exceptionHandlers;

import nl.dubio.exceptions.ClientException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class ClientExceptionHandler implements ExceptionMapper<ClientException> {

    @Override
    public Response toResponse(ClientException exception) {
        return Response.status(exception.getCode())
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN)
                .build();
    }
}
