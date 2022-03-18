package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.common.RestAdapter;
import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Optional;

@Path("/speaker")
@ApplicationScoped
@Produces({MediaType.APPLICATION_JSON})
@Consumes(MediaType.APPLICATION_JSON)
@RestAdapter
public class SpeakerResource implements SpeakerServiceProxy {

    @Inject
    private SpeakerServiceProxy speakerServiceProxy;

    @Override
    public List<Speaker> getAll() {
        return speakerServiceProxy.getAll();
    }

    public Optional<Speaker> findById(String id) {
        return speakerServiceProxy.findById(id);
    }

    @Override
    public List<Speaker> search(Speaker speaker) {
        return speakerServiceProxy.search(speaker);
    }

    @Override
    public Speaker add(Speaker speaker) {
        return speakerServiceProxy.add(speaker);
    }

    @Override
    public void update(Speaker speaker) {
        speakerServiceProxy.update(speaker);
    }

    @Override
    public void delete(String id) {
        speakerServiceProxy.delete(id);
    }
}
