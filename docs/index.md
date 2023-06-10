# A Quick Guide to Kickstart a Jakarta EE 10 Application

In the past years, I have maintained several starter boilerplate projects for Jakarta EE developers, including:

* [Jakarta EE 8 Starter Boilerplate](https://github.com/hantsy/jakartaee8-starter-boilerplate)
* [Jakarta EE 9 Starter Boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate) 
* [Jakarta EE 9 Servlet Starter Boilerplate](https://github.com/hantsy/jakartaee9-servlet-starter-boilerplate)(for Servlet containers only, such as Jetty, Apache Tomcat). 

With these starter boilerplate projects, it is easy to start a new Jakarta EE project with SDLC support. 

Jakarta EE 10 was released for a few months, the most popular Jakarta EE providers, such as GlassFish v7, WildFly 27/28.x, OpenLiberty 23.0.0.4, etc. have been aligned with the newest Jakarta EE 10 specification. I think it is time to upgrade the existing [Jakarta EE 9 Starter Boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate), and provide an update for Jakarta EE 10.

The new [Jakarta EE 10 Starter Boilerplate](https://github.com/hantsy/jakartaee10-starter-boilerplate) is available on Github. Initially the project contains configuration for three of the most popular open-source application servers: 

* [Eclipse GlassFish](https://github.com/eclipse-ee4j/glassfish)
* [WildFly](https://www.wildfly.org/)
* [OpenLiberty](https://www.openliberty.org/)

Other Jakarta EE providers, such as Apache TomEE, etc. will be updated in future when it is updated to Jakarta EE 10.

Next let's go through this project.

## Getting Started

I assume you have installed the following software.

* [Git](https://git-scm.com/)
* JDK 17+
* The latest [Apache Maven](https://maven.apache.org)
* Your favorite text Editor or Java IDE

To start any development work, you have to prepare the development environment firstly. 

## Exploring Project Structure

Open a terminal, clone a copy of the source codes.

```bash
git clone https://github.com/hantsy/jakartaee10-starter-boilerplate
```

Import the source codes into IDE, such as IntelliJ IDEA, you will see the following file structure.

![idea-structure](./idea1.png)

In contrast to [Jakarta EE 9 Starter Boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate), the example codes are enriched. Beside simple CDI and Rest examples, I've added example codes to demonstrate JMS, EJB and JPA, etc.

1. It is a standard Maven project, in the project root folder, there is a *pom.xml* to manage Maven build lifecycle.
2. Under the *main/src/java* folder, expand the package `com.example.demo`, it contains several subpackages.

    * `ejb` is an example of Stateless EJB to perform database CRUD operations. *NOTE: EJB is be deprecated in the further Jakarta EE*.
    * `cdi` is to replace the `ejb` functionality, the codes are rewritten in regular CDI beans.
    * `domain` contains a simple `Todo` JPA Entity and some helper classes.
    * `web` includes Faces backing beans example.
    * `rest` includes Restful API example.
    * `jms` contains a simple JMS example.

3. Under the *test* folder, it contains testing codes and resources to run Arquillian tests.
4. Under the *.github/workflows*, there are several Github actions workflow for building to build the project and running tests against different Arquillian container adapters.
5. There are several Docker image definition file which prefix is *Dockerfile.*, which is used to build the application into a Docker image.
6. The *docker-compose.yaml* defines services to run GlassFish, WildFLy, OpenLiberty in docker container.

## Build and Run
In the docs of [Jakarta EE 9 starter boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate), it introduced how to deploy to GlassFish, Payara, OpenLiberty servers, including managed and running servers. 

In this Jakarta EE 10 Starter Boilerplate, it only includes configuration for deploying application on local managed servers which is every common in the development stage.

### GlassFish

For example, run the following command to build the project and deploy application on a GlassFish server.

```bash 
mvn clean package cargo:run -Pglassfish
...
[INFO]
[INFO] --- cargo:1.10.7:run (default-cli) @ demo ---
[INFO] [en3.ContainerRunMojo] Resolved container artifact org.codehaus.cargo:cargo-core-container-glassfish:jar:1.10.7 for container glassfish7x
[INFO] [talledLocalContainer] Parsed GlassFish version = [7.0.4]
[INFO] [talledLocalContainer] GlassFish 7.0.4 starting...
[INFO] [talledLocalContainer] Attempting to start cargo-domain.... Please look at the server log for more details.....
[INFO] [talledLocalContainer] GlassFish 7.0.4 started on port [8080]
[INFO] Press Ctrl-C to stop the container...
```
Open another terminal window, use `curl` to verify the Todo Restful API example.

```bash
# curl http://localhost:8080/demo/api/todos
[{"id":"c34b0111-f4af-46b6-9749-acf4eba8077e","completed":false,"title":"Say Hello to Jakarta EE 10"}]
```

More details of deploying Jakarta EE applications on GlassFish, check [Deploying to GlassFish v6.0 using Cargo maven plugin](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/deploy-cargo.md) and [Remote Deployment to GlassFish v6.0 using Cargo local deployer](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/deploy-cargo-gf6.md).
### WildFly

[WildFly](https://www.wildfly.org) project itself provides a great maven plugin to deploy Jakarta EE application to an embedded server, an existing server, or a running server.

Run the following command to deploy the application to a WildFly server.

```bash 
mvn clean wildfly:run -Pwildfly
```

More details of deploying to WildFly server, check [Deploying to WildFly](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/deploy-wildfly.md) and [Deploying with WildFly Bootable Jar](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/deploy-wildfly-bootable.md).
### OpenLiberty 

Simply run the following command to run the application on a local managed OpenLiberty server.


```bash 
mvn clean liberty:run -Popenliberty
```

More details of deploying to OpenLiberty, check [Deploying to OpenLiberty](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/deploy-openliberty.md).

## Testing 

