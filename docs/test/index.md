# Testing with Arquillian and JUnit 5

I have written a couple of posts to describe how to test Jakarta components with Arquillian container adapters before. 

> If you are interested in the detailed configuration steps,  please go to the doc section of  [Jakarta EE 8 Starter Boilerplate](https://github.com/hantsy/jakartaee8-starter-boilerplate) and [Jakarta EE 9 Starter Boilerplate](https://github.com/hantsy/jakartaee9-starter-boilerplate). 

In this new Jakarta EE 10 Starter Boilerplate project, I have ported the following Arquillian container adapters configuration with the latest application servers.

* GlassFish Managed Container
* GlassFish Remote Container
* WildFly Managed Container
* WildFly Remote Container
* OpenLiberty Managed Container
* OpenLiberty Remote Container

## GlassFish Managed Container

In this case,the test will manage the GlassFish container lifecycle, start, deploy, run test, undeploy, stop.

Run the following command to run tests against a GlassFish managed container adapter.

```bash 
mvn clean verify -Parq-glassfish-managed
```

## GlassFish Remote Container

Make sure there is a running GlassFish server.

Run the following command to run tests against a GlassFish remote container adapter.

```bash 
mvn clean verify -Parq-glassfish-managed
```

## WildFly Managed Container

Similarly run the following command to run tests against a WildFly managed container adapter.

```bash 
mvn clean verify -Parq-wildfly-managed
```

## GlassFish Remote Container

Make sure there is a running WildFly server.

Execute the following command to add an administrator user.

```bash
<WILDFLY_INSTALLDIR>/bin/add-user.sh admin Admin@123 --silent
```

Then run the following command to run tests against a WildFly remote container adapter.

```bash 
mvn clean verify -Parq-wildfly-managed
```

## OpenLiberty Managed Container

There is a specific *test/arq-liberty-managed/server.xml* file prepared for the OpenLiberty managed container adapter. In the feature list, it adds extra `local-connector` and `usr:arquillian-support-jakarta-2.0` features for support connection to a local server.

Similarly run the following command to run tests against a OpenLiberty managed container adapter.

```bash 
mvn clean verify -Parq-liberty-managed
```

## OpenLiberty Remote Container

Similarly there is a *test/arq-liberty-remote/server.xml* file prepared for the OpenLiberty remote container adapter. In the feature list, add a `rest-connector` to support connection via REST protocol to a running server.

In the server.xml file, we also enabled SSL support, but the OpenLiberty generated security certificates are not recognized by client(the JVM to run tests), we need to extract cert file from OpenLiberty, and import into the client Java security folder. The detailed steps, please refer the doc [Testing with Arquillian and OpenLiberty](https://github.com/hantsy/jakartaee9-starter-boilerplate/blob/master/docs/arq-openliberty.md).

Make sure the OpenLiberty is running.

Run the following command to run tests against an OpenLiberty remote container adapter.

```bash
mvn clean verify -Parq-liberty-remote
```