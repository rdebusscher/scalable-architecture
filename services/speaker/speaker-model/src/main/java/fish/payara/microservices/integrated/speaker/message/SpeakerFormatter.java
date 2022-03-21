package fish.payara.microservices.integrated.speaker.message;

import fish.payara.microservices.integrated.common.messages.EntityMessageFormatter;
import fish.payara.microservices.integrated.speaker.model.Speaker;

/**
 * Formats a Speaker Object to String for Error message purposes.  Loaded through ServiceLoader mechanism (configured
 * in this project through META-INF/services entry.
 */
public class SpeakerFormatter implements EntityMessageFormatter {
    @Override
    public boolean supportsEntity(Object entity) {
        return Speaker.class.isAssignableFrom(entity.getClass());
    }

    @Override
    public String messageForEntity(Object entity) {
        Speaker speaker = (Speaker) entity;
        StringBuilder result = new StringBuilder();
        result.append("Speaker with ");
        if (speaker.getId() != null) {
            result.append("id = '").append(speaker.getId()).append("' ");
        }
        result.append("nameFirst = '").append(speaker.getNameFirst()).append('\'');
        result.append(" nameLast = '").append(speaker.getNameLast()).append('\'');
        result.append(" organization = '").append(speaker.getOrganization()).append('\'');
        return result.toString();
    }
}
