package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.speaker.model.Speaker;

import java.util.List;
import java.util.Optional;

/**
 * Read Operations that can be performed on a Speaker.
 */
public interface SpeakerData {

    List<Speaker> getAll();

    Optional<Speaker> getSpeaker(String speakerId);

    /**
     * Retrieves a speaker based on the properties name and/or organization.
     * @param speaker Speaker data to be matched
     * @return List of matching speakers or empty list hen no match.
     */
    List<Speaker> find(Speaker speaker);
}
