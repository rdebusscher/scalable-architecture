package fish.payara.microservices.integrated.common;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Queue Adapter is needed to distinguish between the CDI bean that forwards to the Hazelcast Queue and the actual
 * Backend implementation (that is marked with {@code Local}).
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface QueueAdapter {

    class Literal extends AnnotationLiteral<QueueAdapter> implements QueueAdapter {
    }

}
