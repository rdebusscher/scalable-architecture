package fish.payara.microservices.integrated.common.exception;

import fish.payara.microservices.integrated.common.messages.EntityMessageFormatterFactory;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(Object entity) {
        super(String.format("Entity not found : '%s'", EntityMessageFormatterFactory.getEntityIdText(entity)));
    }

    public EntityNotFoundException(String entityType, String id) {
        super(String.format("Entity with id '%s' of type '%s' not found", id, entityType));
    }

    /**
     * Should only be used when re-creating Exception from JSON error response.
     * @param message The message of the Exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }

}
