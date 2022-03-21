package fish.payara.microservices.integrated.speaker.broker;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import fish.payara.microservices.integrated.common.CommandEventEntity;
import fish.payara.microservices.integrated.common.EntityType;
import fish.payara.microservices.integrated.common.QueueAdapter;
import fish.payara.microservices.integrated.speaker.SpeakerBackend;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.util.UUID;

@ApplicationScoped
@QueueAdapter
@Default
public class HazelcastSpeakerBackend implements SpeakerBackend {

    @Inject
    private HazelcastInstance hazelcastInstance;

    @Override
    public Speaker add(Speaker speaker) {
        speaker.setId(UUID.randomUUID().toString());
        IQueue<Object> queue = hazelcastInstance.getQueue(QueueNames.SPEAKER_QUEUE_NAME);
        try {
            queue.put(new CommandEventEntity(speaker, EntityType.ADD));
        } catch (InterruptedException e) {
            e.printStackTrace();
            // FIXME
        }

        return speaker;
    }

    @Override
    public void update(Speaker speaker) {
        // FIXME Implement
    }

    @Override
    public void delete(Speaker speaker) {
        // FIXME Implement
    }
}
