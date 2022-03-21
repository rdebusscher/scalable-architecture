# Data model and operations for Speakers.

- `model.speaker` : The Speaker 'entity'.
- `SpeakerData` : Interface - Read and Search operations for Speakers.
- `SpeakerBackend` : Interface - Operations for a Speaker that change the data.

The interfaces needs to be implemented by `CDI services`. Depending on the packaging, this can be a call to another microservice with Rest Client, put on a Messaging Queue or the actual implementation of the logic.

