# Packaging variations

Based on the different building blocks available in the project https://github.com/rdebusscher/scalable-architecture/services different packagings can be created wthout the need to rewrite the application.


- monolith: a Single Web application containing all modules.
- step1 : Packaging in 2 artifacts; a Web application with all the screens and a service with Proxy and Backend for Speaker domain (Session domain not included in example)
- step2 : Packaging in 3 artifacts; A Web application with all the screens and Proxy and Backend code in different applications.

In this example, not all aspects are integrated

- For the read-only database of the Proxy, data is kep in memory.
- The real database is not implemented
- No SAGA implementation with the AxonIQ framework.


Possible improvements

- The JSF exception handling is repeated in each Application containing the JSF screens.