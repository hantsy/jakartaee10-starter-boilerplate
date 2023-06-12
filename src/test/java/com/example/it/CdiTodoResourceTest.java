package com.example.it;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URL;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.cdi.CdiTodoRepository;
import com.example.domain.Todo;
import com.example.ejb.EjbTodoRepository;
import com.example.rest.CdiTodoResource;
import com.example.rest.CreateTodoCommand;
import com.example.rest.RestActivator;
import com.example.rest.TodoNotFoundExceptionMapper;
import com.example.rest.UpdateTodoCommand;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@ExtendWith(ArquillianExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CdiTodoResourceTest {
    private final static Logger LOGGER = Logger.getLogger(CdiTodoResourceTest.class.getName());

    @Deployment(testable = false)
    public static WebArchive createDeployment() {
        WebArchive war = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Todo.class.getPackage())
                .addPackage(EjbTodoRepository.class.getPackage())
                .addPackage(CdiTodoRepository.class.getPackage())
                .addClasses(
                        CdiTodoResource.class,
                        CreateTodoCommand.class,
                        RestActivator.class,
                        UpdateTodoCommand.class,
                        TodoNotFoundExceptionMapper.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
        LOGGER.log(Level.INFO, "war archive: {0}", war.toString(true));
        return war;
    }

    @ArquillianResource
    URL baseUrl;

    private Client client;

    @BeforeEach
    public void setup() {
        LOGGER.log(Level.INFO, "base URL:{0}", new Object[] { baseUrl });
        this.client = ClientBuilder.newClient();
        // this.client.register()
    }

    @AfterEach
    public void teardown() {
        if (this.client != null) {
            this.client.close();
        }
    }

    @Test
    @Order(1)
    public void testTodosAPI() throws Exception {
        LOGGER.log(Level.INFO, " Running test:: testTodosAPI ... ");
        final WebTarget allTodosTarget = client.target(new URL(baseUrl, "api/cditodos").toExternalForm());
        try (final Response allTodos = allTodosTarget.request()
                .accept(MediaType.APPLICATION_JSON)
                .get()) {
            assertEquals(200, allTodos.getStatus());
            assertEquals(2, allTodos.readEntity(new GenericType<List<Todo>>() {/* empty body */
            }).size());
        }
    }

    @Test
    @Order(2)
    public void testTodoNotFound() throws Exception {
        LOGGER.log(Level.INFO, " Running test:: testTodoNotFound ... ");
        final WebTarget getByIdTarget = client
                .target(new URL(baseUrl, "api/cditodos/" + UUID.randomUUID()).toExternalForm());
        try (final Response getById = getByIdTarget.request()
                .accept(MediaType.APPLICATION_JSON)
                .get()) {
            assertEquals(404, getById.getStatus());
        }
    }

    @Test
    @Order(3)
    public void testCreateTodoAPI() throws Exception {
        LOGGER.log(Level.INFO, " Running test:: testCreateTodoAPI ... ");
        final WebTarget createTodoTarget = client.target(new URL(baseUrl, "api/cditodos").toExternalForm());
        var body = new CreateTodoCommand("test");
        try (final Response createTodoResponse = createTodoTarget.request()
                .post(Entity.json(body))) {
            assertEquals(201, createTodoResponse.getStatus());
            assertNotNull(createTodoResponse.getLocation());
        }
    }

}
