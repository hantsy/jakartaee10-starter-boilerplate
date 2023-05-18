package com.example.jms;

import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.jms.JMSDestinationDefinition;
import jakarta.jms.JMSDestinationDefinitions;

@JMSDestinationDefinitions({
        @JMSDestinationDefinition(
                name = "java:app/jms/HelloQueue",
                // resourceAdapter = "jmsra",
                interfaceName = "jakarta.jms.Queue",
                destinationName = "HelloQueue")
})
@Singleton
@Startup
public class JmsResources {
}
