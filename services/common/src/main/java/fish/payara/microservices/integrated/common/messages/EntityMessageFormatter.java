package fish.payara.microservices.integrated.common.messages;

/**
 * Define how a Domain entity is printed out in error message. Based on the 'important' fields.
 * Implementations of this interface are picked up by ServiceLoader and implementation should be available in
 * <domain>-model module.
 */
public interface EntityMessageFormatter {

    boolean supportsEntity(Object entity);

    String messageForEntity(Object entity);
}
