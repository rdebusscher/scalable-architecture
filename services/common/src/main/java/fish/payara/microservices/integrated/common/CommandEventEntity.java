package fish.payara.microservices.integrated.common;

import java.io.Serializable;

public class CommandEventEntity implements Serializable {
// We cannot use a parameterized type in CDI events.

    private Serializable entity;
    private EntityType entityType;

    // For serialization
    public CommandEventEntity() {
    }

    public CommandEventEntity(Serializable entity, EntityType entityType) {
        this.entity = entity;
        this.entityType = entityType;
    }

    public Serializable getEntity() {
        return entity;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntity(Serializable entity) {
        this.entity = entity;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }
}
