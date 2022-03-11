package fish.payara.microservices.integrated.events.hazelcast;

import fish.payara.microservices.integrated.common.CommandEventEntity;
import fish.payara.microservices.integrated.common.EntityType;

import javax.json.*;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//@ApplicationScoped
// results in "No valid EE environment for injection of fish.payara.microservices.integrated.events.hazelcast.EventSerializer"
public class EventSerializer {

    //When putting the CommandEventEntity instance on the Hazelcast Topic, the other Hazelcast members
    // receive the message but the message is empty (null)
    // This doesn't happen in Java SE, so there must be an issue with Payara Micro and serialization.
    // The workaround is to create a JSON string and put that on the Topic.

    private Jsonb jsonb;
    private JsonWriterFactory writerFactory;

    public void init() {
        jsonb = JsonbBuilder.newBuilder().build();

        Map<String, ?> config = new HashMap<>();
        writerFactory = Json.createWriterFactory(config);


    }

    public String generatePayload(CommandEventEntity commandEventEntity) {

        String entityJSON = jsonb.toJson(commandEventEntity.getEntity());
        JsonObject json = Json.createObjectBuilder()
                .add("type", commandEventEntity.getEntityType().name())
                .add("class", commandEventEntity.getEntity().getClass().getName())
                .add("entity", entityJSON).build();

        String result;

        try (Writer stringWriter = new StringWriter();
             JsonWriter jsonWriter = writerFactory.createWriter(stringWriter)) {
            jsonWriter.write(json);
            result = stringWriter.toString();
        } catch (IOException e) {
            result = e.toString();  // This is not ideal.
        }
        return result;
    }

    public CommandEventEntity parsePayload(String json) {
        JsonReader jsonReader = Json.createReader(new StringReader(json));

        JsonObject jsonObject = jsonReader.readObject();

        CommandEventEntity result = null;

        try {
            Serializable o = (Serializable) jsonb.fromJson(jsonObject.getString("entity"), Class.forName(jsonObject.getString("class")));
            EntityType type = EntityType.valueOf(jsonObject.getString("type"));
            result = new CommandEventEntity(o, type);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  // FIXME
        }
        return result;
    }


}
