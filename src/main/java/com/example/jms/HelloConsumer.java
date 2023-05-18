package com.example.jms;


import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import java.util.logging.Level;
import java.util.logging.Logger;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(
                        propertyName = "destinationType",
                        propertyValue = "jakarta.jms.Queue"),
                @ActivationConfigProperty(
                        propertyName = "destinationLookup",
                        propertyValue = "java:app/jms/HelloQueue")
        }
)
public class HelloConsumer implements MessageListener {
    private static final Logger LOGGER = Logger.getLogger(HelloConsumer.class.getName());
    
    @Override
    public void onMessage(Message message) {
        try {
            LOGGER.log(Level.INFO, "received message: {0}", message.getBody(String.class));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
