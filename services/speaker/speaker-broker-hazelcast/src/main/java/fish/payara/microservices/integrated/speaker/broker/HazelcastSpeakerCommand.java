package fish.payara.microservices.integrated.speaker.broker;

import com.hazelcast.collection.IQueue;
import com.hazelcast.core.HazelcastInstance;
import fish.payara.microservices.integrated.common.CommandEventEntity;
import fish.payara.microservices.integrated.common.Local;
import fish.payara.microservices.integrated.speaker.SpeakerBackend;
import fish.payara.microservices.integrated.speaker.model.Speaker;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;

@ApplicationScoped
public class HazelcastSpeakerCommand {

    @Inject
    private HazelcastInstance hazelcastInstance;

    @Inject
    private Instance<SpeakerBackend> speakerBackend;

    @Inject
    @ConfigProperty(name = "speaker.queue.pump")
    private boolean speakerQueuePumpActive;

    public void onStart(@Observes @Initialized(ApplicationScoped.class) Object pointless) {
        if (!speakerQueuePumpActive) {
            return;
        }
        IQueue<CommandEventEntity> queue = hazelcastInstance.getQueue(QueueNames.SPEAKER_QUEUE_NAME);

        // FIXME ?? How to use ManagedExecutorService on Payara Micro?
        //executor.execute(new QueuePump(queue, speakerBackend));
        new Thread(new QueuePump(queue, speakerBackend.select(new Local.Literal()).get())).start();
    }

    private static class QueuePump implements Runnable {

        private IQueue<CommandEventEntity> queue;
        private SpeakerBackend speakerBackend;

        public QueuePump(IQueue<CommandEventEntity> queue, SpeakerBackend speakerBackend) {
            this.queue = queue;
            this.speakerBackend = speakerBackend;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    CommandEventEntity command = queue.take();
                    handleCommand(command);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    // FIXME
                }
            }
        }

        private void handleCommand(CommandEventEntity command) {
            Speaker speaker = (Speaker) command.getEntity();
            switch (command.getEntityType()) {

                case ADD:
                    speakerBackend.add(speaker);
                    break;
                case UPDATE:
                    speakerBackend.update(speaker);
                    break;
                case DELETE:
                    speakerBackend.delete(speaker);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }
    }
}
