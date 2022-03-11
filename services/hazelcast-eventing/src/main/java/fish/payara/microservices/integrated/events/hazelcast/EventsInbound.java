package fish.payara.microservices.integrated.events.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import com.hazelcast.topic.Message;
import com.hazelcast.topic.MessageListener;
import fish.payara.microservices.integrated.common.CommandEventEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class EventsInbound implements MessageListener<String> {

    @Inject
    private HazelcastInstance hazelcastInstance;

    private EventSerializer eventSerializer;

    @Inject
    @ConfigProperty(name = "events.inbound.active")
    private boolean inboundEventActive;

    @Inject
    private Event<CommandEventEntity> events;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        if (inboundEventActive) {
            eventSerializer = new EventSerializer();
            eventSerializer.init();

            ITopic<String> topic = hazelcastInstance.getTopic("events");
            topic.addMessageListener(this);
        }
    }

    @Override
    public void onMessage(Message<String> message) {
        events.fireAsync(eventSerializer.parsePayload(message.getMessageObject()));
    }
}
