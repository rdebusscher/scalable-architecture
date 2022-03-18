package fish.payara.microservices.integrated.packaging;

import fish.payara.microservices.integrated.common.exception.ExceptionJSONMessage;

import javax.json.bind.JsonbBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Converts a JAX-RS response with status 412 to a Business Exception.(and throws it)
 */
public class ExceptionResponseFilter implements ClientResponseFilter {

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        int status = responseContext.getStatus();
        if (status == Response.Status.PRECONDITION_FAILED.getStatusCode()) {
            // Status 412 -> create Exception for response.
            String body = new String(responseContext.getEntityStream().readAllBytes(), StandardCharsets.UTF_8);
            ExceptionJSONMessage exceptionJSONMessage = JsonbBuilder.create().fromJson(body, ExceptionJSONMessage.class);
            throw exceptionJSONMessage.toBusinessException();
        }
    }
}
