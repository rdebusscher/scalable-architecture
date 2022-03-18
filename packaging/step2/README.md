# Front - Proxy - Backend service

Packaging where the frontend runs in a separate instance and Proxy and Backend is also seperated..

-speaker-backend : Project with Backend. Containing `speaker-backend` for the logic and the infrastructure helper modules `speaker-broker-hazelcast`(Read and Write from/to Queue supported by Hazelcast) and `hazelcast-eventing`(Read and Write from/to Topic supported by Hazelcast).
-speaker-proxy : Project with Proxy. Containing `speaker-proxy`, `speaker-backend`, `speaker-data-memory` services modules and the infrastructure helper modules `speaker-broker-hazelcast`(Read and Write from/to Queue supported by Hazelcast) and `hazelcast-eventing`(Read and Write from/to Topic supported by Hazelcast).
- webapp : Project with frontend. Containing `speaker-app` service module. 

## Speaker Backend Service

Web Application that contains the service module but no other code. Through MicroProfile Config the modules `speaker-broker-hazelcast` and `hazelcast-eventing` are configured. (reading from queue and send to topics) 

## Speaker Proxy Service

The Speaker Service has an additional JAX-RS resource so that the implementation of the `SpeakerServiceProxy` can be accessed remotely.  The implementation just forwards to the implementation.

An `ExceptionMapper` is implemented to convert any BusinessException to a JAX-RS response with status 412 (PRECONDITION_FAILED) and as body the JSON representation of the Exception (class name and Exception message) 

## Front End Service

2 additional classes are added, in addition to the JSF pages that include the JSF fragments from the building block.

A `ClientResponseFilter` implementation that converts an HTTP JAX-RS response with status 412 back to an exception that will handled by the JSF `ExceptionHandler` to show the message.  

A CDI Provider that generate a Rest Client proxy (Using MicroProfile Rest Client) that forwards any access to the Proxy service through REST.  Configuration of the Speaker Service URL is done through a MicroProfile Config key. 


