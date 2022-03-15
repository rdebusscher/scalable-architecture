package fish.payara.microservices.integrated.common.exception;

import fish.payara.microservices.integrated.common.messages.EntityMessageFormatterFactory;

public class EntityAlreadyExistException extends BusinessException {

    public EntityAlreadyExistException(Object entity) {
        super(String.format("Entity already created : '%s'", EntityMessageFormatterFactory.getEntityIdText(entity)));
    }

    /**
     * Should only be used when re-creating Exception from JSON error response.
     * @param message The message of the Exception
     */
    public EntityAlreadyExistException(String message) {
        super(message);
    }
}
