package fish.payara.microservices.integrated.events.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.topic.ITopic;
import fish.payara.microservices.integrated.common.CommandEventEntity;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.ObservesAsync;
import javax.inject.Inject;

@ApplicationScoped
public class EventsOutbound {

    @Inject
    private HazelcastInstance hazelcastInstance;

    @Inject
    @ConfigProperty(name = "events.outbound.active")
    private boolean inboundEventActive;

    private EventSerializer eventSerializer;

    @PostConstruct
    public void init() {
        eventSerializer = new EventSerializer();
        eventSerializer.init();
    }

    public void speakerEvent(@ObservesAsync CommandEventEntity event) {
        if (inboundEventActive) {
            ITopic<String> events = hazelcastInstance.getTopic("events");
            events.publish(eventSerializer.generatePayload(event));
        }
    }
}
