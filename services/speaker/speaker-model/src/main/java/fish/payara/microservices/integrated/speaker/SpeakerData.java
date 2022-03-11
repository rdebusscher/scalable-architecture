package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.speaker.model.Speaker;

import java.util.List;
import java.util.Optional;

public interface SpeakerData {

    List<Speaker> getAll();

    Optional<Speaker> getSpeaker(String speakerId);

    List<Speaker> find(Speaker speaker);
}
