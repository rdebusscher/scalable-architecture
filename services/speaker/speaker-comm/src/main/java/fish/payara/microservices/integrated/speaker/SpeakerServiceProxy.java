package fish.payara.microservices.integrated.speaker;

import fish.payara.microservices.integrated.speaker.model.Speaker;

import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

public interface SpeakerServiceProxy {

    @GET
    @Path("/all")
    List<Speaker> getAll();

    @GET
    @Path("/retrieve/{id}")
    Optional<Speaker> findById(@PathParam("id") String id);

    @PUT
    @Path("/search")
    List<Speaker> search(Speaker speaker);

    @POST
    @Path("/add")
    Speaker add(Speaker speaker);

    @PUT
    @Path("/update")
    void update(Speaker speaker);

    @DELETE
    @Path("/remove/{id}")
    void delete(@PathParam("id") String id);
}
