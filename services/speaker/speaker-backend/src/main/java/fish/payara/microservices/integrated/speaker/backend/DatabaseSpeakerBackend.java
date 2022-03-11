package fish.payara.microservices.integrated.speaker.backend;

import fish.payara.microservices.integrated.common.CommandEventEntity;
import fish.payara.microservices.integrated.common.EntityType;
import fish.payara.microservices.integrated.common.Local;
import fish.payara.microservices.integrated.speaker.SpeakerBackend;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.inject.Default;
import javax.inject.Inject;

@ApplicationScoped
@Local
@Default
public class DatabaseSpeakerBackend implements SpeakerBackend {

    @Inject
    private Event<CommandEventEntity> event;

    @Override
    public Speaker add(Speaker speaker) {
        // Store to database
        // TODO implement, not done for this study.

        // Send out event so that read-only database can update themselves.
        event.fireAsync(new CommandEventEntity(speaker, EntityType.ADD));
        return speaker;
    }

    @Override
    public void update(Speaker speaker) {
        System.out.println("DatabaseSpeakerBackend update " + speaker);
        // FIXME TODO
    }

    @Override
    public void delete(Speaker speaker) {
        System.out.println("DatabaseSpeakerBackend delete " + speaker);
        // FIXME TODO
    }
}
