package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.speaker.model.Speaker;

public interface SpeakerBackend {
    Speaker add(Speaker speaker);

    void update(Speaker speaker);

    void delete(Speaker speaker);
}
