package fish.payara.microservices.integrated.speaker.app;

import fish.payara.microservices.integrated.speaker.SpeakerServiceProxy;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class SpeakerView {

    @Inject
    private SpeakerServiceProxy serviceProxy;

    public List<Speaker> getSpeakers() {
        return serviceProxy.getAll();
    }
}
