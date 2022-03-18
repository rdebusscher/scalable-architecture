package fish.payara.microservices.integrated;

import fish.payara.microservices.integrated.common.exception.BusinessException;
import fish.payara.microservices.integrated.common.exception.ExceptionJSONMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convert a _BusinessException_  into a HTTP response with status 412 and payload containing the Exception class name and message.
 */
@Provider
public class BusinessExceptionHandler implements ExceptionMapper<BusinessException> {
    @Override
    public Response toResponse(BusinessException exception) {
        return Response.status(Response.Status.PRECONDITION_FAILED)
                .type(MediaType.APPLICATION_JSON)
                .entity(new ExceptionJSONMessage(exception.getClass().getSimpleName(), exception.getMessage()))
                .build();
    }
}
