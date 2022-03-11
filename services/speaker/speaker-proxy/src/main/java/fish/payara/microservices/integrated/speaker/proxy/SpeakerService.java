package fish.payara.microservices.integrated.speaker.proxy;

import fish.payara.microservices.integrated.common.exception.EntityAlreadyExistException;
import fish.payara.microservices.integrated.common.exception.EntityNotFoundException;
import fish.payara.microservices.integrated.speaker.SpeakerBackend;
import fish.payara.microservices.integrated.speaker.SpeakerData;
import fish.payara.microservices.integrated.speaker.SpeakerServiceProxy;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class SpeakerService implements SpeakerServiceProxy {

    @Inject
    private SpeakerData speakerData;

    @Inject
    private Instance<SpeakerBackend> speakerBackendProvider;

    private SpeakerBackend speakerBackend;

    @PostConstruct
    public void init() {
        // We always have 1 instance of the SpeakerBackend in the app. But in 1 situation
        // the instance has a qualifier. By selecting it through Instance<SpeakerBackend>
        // we ignore the qualifier.
        speakerBackend = speakerBackendProvider.get();
    }

    @Override
    public List<Speaker> getAll() {
        return speakerData.getAll();
    }

    @Override
    public Optional<Speaker> findById(String id) {
        return speakerData.getSpeaker(id);
    }

    @Override
    public List<Speaker> search(Speaker speaker) {
        return speakerData.find(speaker);
    }

    @Override
    public Speaker add(Speaker speaker) {

        List<Speaker> matchingSpeaker = search(speaker);
        if (!matchingSpeaker.isEmpty()) {
            throw new EntityAlreadyExistException();
        }
        speaker.setId(null); // Make sure this is not set.
        return speakerBackend.add(speaker);
    }

    @Override
    public void update(Speaker speaker) {
        Optional<Speaker> byId = findById(speaker.getId());

        if (byId.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<Speaker> matchingSpeaker = search(speaker);
        if (!matchingSpeaker.isEmpty()) {
            // If a speaker found that matches name and organization but has a different id -> Update to already existing name.
            if (matchingSpeaker.get(0).getId().equals(speaker.getId())) {
                throw new EntityAlreadyExistException();
            }
        }

        speakerBackend.update(speaker);
    }

    @Override
    public void delete(String id) {
        Optional<Speaker> byId = findById(id);

        if (byId.isEmpty()) {
            throw new EntityNotFoundException();
        }

        speakerBackend.delete(byId.get());
    }
}
