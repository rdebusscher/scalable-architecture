# Monolith Packaging

All the building blocks are included in a single WAR file.  They are based on the different building blocks available in the project https://github.com/rdebusscher/scalable-architecture/services. Different packagings can be created without the need to rewrite the application.

For the JSF application, the templating functionality is used to have a uniform layout of all pages.  The content of the screens are coming from the _speaker-app_  module within the services.  The content are is replaced by the fragment defined in that services module.

````
        <ui:define name="content">
            <ui:include src="/app/speakerList.xhtml"/>
        </ui:define>
````

Packaging is based on the building blocks

-speaker-app : The screens
-speaker-proxy : The implementation of the Proxy of the Speaker domain.
-speaker-data-memory : The in memory version of the Readonly database storage of the Proxy. 
-speaker-backend : The implementation of the Backend of the Speaker domain.
