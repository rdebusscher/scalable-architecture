package fish.payara.microservices.integrated.common.exception;

/**
 * Super class for all Business related exeption. This allows easy detection of such an exception and propagation over
 * the REST interfaces in case where different processes are involced.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
