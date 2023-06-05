# Building and Running Application

In the docs of [Jakarta EE 9 starter boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate), it introduced how to deploy to GlassFish, Payara, OpenLiberty servers, including managed and running servers(remote). 

In this Jakarta EE 10 Starter Boilerplate, we just includes simple deploy and run the sample application on local managed servers, it is every common in the development stage.

## Glassfish 

We usually use [cargo maven plugin](https://codehaus-cargo.github.io/cargo/Maven+3+Plugin.html) to run a Jakarta EE project on GlassFish, because there is no official maven plugin for it.

Run the following command to build and deploy to a GlassFish server.

```bash 
mvn clean package cargo:run -Pglassfish
```

## WildFly

[WildFly](https://www.wildfly.org) project itself provides a great maven plugin to deploy Jakarta EE application to an embedded server, an existing server, or a running server.

Run the following command to deploy the application to a WildFly server.

```bash 
mvn clean wildfly:run -Pwildfly
```

## OpenLiberty 

Simply run the following command to run the application on an OpenLiberty server.


```bash 
mvn clean liberty:run -Popenliberty
```