package com.example.it;

import com.example.jms.HelloSender;
import com.example.jms.JmsResources;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import jakarta.annotation.Resource;
import jakarta.inject.Inject;
import jakarta.jms.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ArquillianExtension.class)
public class HelloJmsTest {
    private final static Logger LOGGER = Logger.getLogger(HelloJmsTest.class.getName());
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(JmsResources.class)
                // .addClass(HelloConsumer.class)
                .addClass(HelloSender.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    
    @Inject
    HelloSender sender;
    
    @Inject
    JMSContext jmsContext;
    
    @Resource(lookup = "java:app/jms/HelloQueue")
    private Destination helloQueue;
    
    @Test
    public void testHelloQueue() throws JMSException {
        JMSConsumer consumer = jmsContext.createConsumer(helloQueue);
        sender.sayHellFromJms();
        Message message = consumer.receive(1000);
        assertTrue(message instanceof TextMessage);
        String text = message.getBody(String.class);
        LOGGER.info("message text:" + text);
        assertTrue(text.startsWith("Hello JMS"));
        consumer.close();
    }
}
