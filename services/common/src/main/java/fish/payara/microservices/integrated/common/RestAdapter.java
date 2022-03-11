package fish.payara.microservices.integrated.common;

import javax.enterprise.util.AnnotationLiteral;
import javax.inject.Qualifier;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Rest Adapter is needed to qualify the JAX-RS endpoints so that it isn't considered
 * to inject into itself.  See fish.payara.microservices.integrated.speaker.SpeakerResource
 * in step1 and step2 modules of packaging.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.TYPE})
public @interface RestAdapter {

    class Literal extends AnnotationLiteral<RestAdapter> implements RestAdapter {
    }

}
