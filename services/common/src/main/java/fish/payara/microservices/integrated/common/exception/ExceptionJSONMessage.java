package fish.payara.microservices.integrated.common.exception;

public class ExceptionJSONMessage {

    private String exceptionClass;
    private String message;

    public ExceptionJSONMessage() {
    }

    public ExceptionJSONMessage(String exceptionClass, String message) {
        this.exceptionClass = exceptionClass;
        this.message = message;
    }

    public String getExceptionClass() {
        return exceptionClass;
    }

    public void setExceptionClass(String exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BusinessException toBusinessException() {
        BusinessException result = null;

        if (EntityAlreadyExistException.class.getSimpleName().equals(exceptionClass)) {
            result = new EntityAlreadyExistException(message);
        }
        if (EntityNotFoundException.class.getSimpleName().equals(exceptionClass)) {
            result = new EntityNotFoundException(message);
        }

        // No class for name as that would introduce a security vulnerability.
        if (result == null) {
            result = new BusinessException(message);
        }
        return result;
    }
}
