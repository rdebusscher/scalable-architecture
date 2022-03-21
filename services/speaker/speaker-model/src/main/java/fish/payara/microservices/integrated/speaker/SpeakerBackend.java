package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.speaker.model.Speaker;

/**
 * Operations that can be performed on a Speaker to change the value.
 */
public interface SpeakerBackend {

    /**
     * Add speaker to the list of speakers.
     * @param speaker The Speaker to Add
     * @return  Updated object with `id` filled in.
     */
    Speaker add(Speaker speaker);

    void update(Speaker speaker);

    void delete(Speaker speaker);
}
