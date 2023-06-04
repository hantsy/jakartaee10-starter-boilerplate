# Exploring Project Structure

The [Jakarta EE 10 Starter Boilerplate](https://github.com/hantsy/jakartaee10-starter-boilerplate) contains a collection of example codes to demonstrate the basic of Jakarta EE specifications, such as JPA, JMS, CDI, EJB, Restful APIs, Faces, etc. 


## Getting Sources Codes

Open a terminal, run the following command to get a copy of the starter codes.

```bash
git clone https://github.com/hantsy/jakartaee10-starter-boilerplate
```

## Exploring File Structure

Import the source codes into IDE, the following picture is the project file structure after imported into the IDEA.

![idea-structure](./idea1.png)


1. It is a Maven project, so in the root there is a *pom.xml* to manage Maven build lifecycle.
2. Under the *main/src/java* folder, expand the package `com.example.demo`, it contains several subpackages.

    * `ejb` is an example of Stateless EJB to perform database CRUD operations. *NOTE: EJB is be deprecated in the further Jakarta EE*.
    * `cdi` is to replace the `ejb` functionality, the codes are rewritten in regular CDI beans.
    * `domain` contains a simple `Todo` JPA Entity and some helper classes.
    * `web` includes Faces backing beans example.
    * `rest` includes Restful API example.
    * `jms` contains a simple JMS example.

3. Under the *test* folder, it contains testing codes and resources to run Arquillian tests.
4. Under the *.github/workflows*, there are several Github actions workflow config to build the project and run tests against different Arquillian container adapters.
5. There are several Docker image definition file which prefix is *Dockerfile.*, which is used to build the application into a Docker image.
6. The *docker-compose.yaml* defines services to run GlassFish, WildFLy, OpenLiberty in docker container.

