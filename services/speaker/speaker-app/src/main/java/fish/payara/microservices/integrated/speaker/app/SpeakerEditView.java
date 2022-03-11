package fish.payara.microservices.integrated.speaker.app;

import fish.payara.microservices.integrated.speaker.SpeakerServiceProxy;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named
public class SpeakerEditView {

    @Inject
    private SpeakerServiceProxy serviceProxy;

    private Speaker speaker = new Speaker();

    public void addSpeaker() {

        serviceProxy.add(speaker);
    }

    public Speaker getSpeaker() {
        return speaker;
    }
}
