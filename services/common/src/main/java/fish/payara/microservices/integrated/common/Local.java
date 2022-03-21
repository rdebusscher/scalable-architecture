package fish.payara.microservices.integrated.common;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Local is needed to distinguish between the CDI bean that implements the Backend implementation from the CDI bean
 * that forwards to Hazelcast Queue (that is marked with {@code QueueAdapter}).
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})

public @interface Local {

    class Literal extends AnnotationLiteral<Local> implements Local {
    }

}
