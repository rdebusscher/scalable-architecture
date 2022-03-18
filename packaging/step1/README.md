# Front - Backend service

Packaging where the frontend runs in a seperate instance than the backend.

-speaker-service : Project with domain resources. Containing `speaker-proxy`, `speaker-backend`, `speaker-data-memory` services modules.
- webapp : Project with frontend. Containing `speaker-app` service module. 

## Speaker Service

The Speaker Service has an additional JAX-RS resource so that the implementation of the `SpeakerServiceProxy` can be accessed remotely.  The implementation just forwards to the implementation.

An `ExceptionMapper` is implemented to convert any BusinessException to a JAX-RS response with status 412 (PRECONDITION_FAILED) and as body the JSON representation of the Exception (class name and Exception message) 

## Front End Service

2 additional classes are added, in addition to the JSF pages that include the JSF fragments from the building block.

A `ClientResponseFilter` implementation that converts an HTTP JAX-RS response with status 412 back to an exception that will handled by the JSF `ExceptionHandler` to show the message.  

A CDI Provider that generate a Rest Client proxy (Using MicroProfile Rest Client) that forwards any access to the Proxy service through REST.  Configuration of the Speaker Service URL is done through a MicroProfile Config key. 


