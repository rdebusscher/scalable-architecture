# Individual building blocks

Individual building blocks of the application build using a _scalable architecture_.

Classes independent of the domain (Speaker, Session, ... ) and the technology (safe to include in any kind of packaging)

- `common` : Exceptions, _Event_ Payload class for Commands (`CommandEventEntity`) and a few CDI Qualifiers.
- `hazelcast-eventing`: Supporting classes for data Events containing the entity that is changed using Hazelcast Topic.
- `speaker`: set of modules (building blocks) related to the Speaker domain.
- `session`: set of modules (building blocks) related to the Session domain. (not included)