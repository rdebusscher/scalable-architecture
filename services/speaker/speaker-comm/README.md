# Definition of Speaker Proxy functionality

Definition of the interface defining the Speaker Proxy functionality. The interface already contains some JAX-RS annotations so that ??? Producer  can create a proxy that call the actual JAX-RS endpoint in the service if a distributed packaging is realised.

When using in the monolith way, the implementation (can be found in the module _speaker-proxy_ is included in the same process and injected a CDI bean where this interface is used)

