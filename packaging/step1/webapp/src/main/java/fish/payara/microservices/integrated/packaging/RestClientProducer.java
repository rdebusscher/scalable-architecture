package fish.payara.microservices.integrated.packaging;

import fish.payara.microservices.integrated.speaker.SpeakerServiceProxy;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.RestClientBuilder;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.net.URI;

@ApplicationScoped
public class RestClientProducer {

    @Inject
    @ConfigProperty(name="fish.payara.microservices.integrated.speaker.SpeakerServiceProxy")
    private String speakerProxyURL;

    @Produces
    public SpeakerServiceProxy produceSpeakerProxyRestClient() {
        return RestClientBuilder.newBuilder()
                .baseUri(URI.create(speakerProxyURL))
                .register(new ExceptionResponseFilter())  // For converting the BusinessExceptions from microservices back to Exception.
                .build(SpeakerServiceProxy.class);
    }
}
