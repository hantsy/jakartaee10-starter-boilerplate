package com.example.jms;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("hellojms")
public class HelloJmsResource {
    private static final Logger LOGGER = Logger.getLogger(HelloJmsResource.class.getName());
    
    @Inject
    private HelloSender sender;
    
    @GET
    @Path("")
    public String sayHello() {
        LOGGER.log(Level.INFO, "sayHello from HelloJmsResource");
        sender.sayHellFromJms();
        return "sent";
    }
}
