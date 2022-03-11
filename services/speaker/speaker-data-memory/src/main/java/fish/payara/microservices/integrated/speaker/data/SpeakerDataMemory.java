package fish.payara.microservices.integrated.speaker.data;

import fish.payara.microservices.integrated.common.CommandEventEntity;
import fish.payara.microservices.integrated.speaker.SpeakerData;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class SpeakerDataMemory implements SpeakerData {

    private List<Speaker> speakers;

    @PostConstruct
    private void init() {
        speakers = new ArrayList<>();
        Speaker speaker = new Speaker();
        speaker.setId("X1234");
        speaker.setNameFirst("Rudy");
        speaker.setNameLast("De Busscher");
        speaker.setOrganization("Payara");
        speakers.add(speaker);
    }

    @Override
    public List<Speaker> getAll() {
        return speakers;
    }

    @Override
    public Optional<Speaker> getSpeaker(String speakerId) {
        return speakers.stream()
                .filter(s -> speakerId.equals(s.getId()))
                .findAny();
    }

    public void speakerEvent(@ObservesAsync /*@SpeakerEntity*/ CommandEventEntity event) {
        Speaker speaker = (Speaker) event.getEntity();
        switch (event.getEntityType()) {

            case ADD:
                speakers.add(speaker);
                break;
            case UPDATE:
                removeSpeaker(speaker);
                speakers.add(speaker);
                break;
            case DELETE:
                removeSpeaker(speaker);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void removeSpeaker(Speaker speaker) {
        speakers = this.speakers.stream()
                .filter(s -> s.getId().equals(speaker.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Speaker> find(Speaker speaker) {
        return speakers.stream()
                .filter(s -> matchSpeaker(s, speaker))
                .collect(Collectors.toList());

    }

    private boolean matchSpeaker(Speaker speaker, Speaker searchFields) {
        boolean result = true;
        if (searchFields.getNameFirst() != null && !searchFields.getNameFirst().isBlank()) {
            result = searchFields.getNameFirst().equalsIgnoreCase(speaker.getNameFirst());
        }

        if (result && searchFields.getNameLast() != null && !searchFields.getNameLast().isBlank()) {
            result = searchFields.getNameLast().equalsIgnoreCase(speaker.getNameLast());
        }
        if (result && searchFields.getOrganization() != null && !searchFields.getOrganization().isBlank()) {
            result = searchFields.getOrganization().equalsIgnoreCase(speaker.getOrganization());
        }

        return result;
    }
}
