package com.example.it;

import com.example.domain.Todo;
import com.example.ejb.EjbTodoRepository;
import com.example.ejb.EntityRepository;
import jakarta.annotation.Resource;
import jakarta.ejb.EJB;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ArquillianExtension.class)
public class EjbTodoRepositoryTest {
    private final static Logger LOGGER = Logger.getLogger(EjbTodoRepositoryTest.class.getName());

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Todo.class.getPackage())
                .addClasses(EjbTodoRepository.class, EntityRepository.class)
                .addClass(DbUtil.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    EjbTodoRepository todos;

    @PersistenceContext
    EntityManager entityManager;

    @Resource(name = "DefaultDataSource")
    DataSource dataSource;

    @Inject
    UserTransaction utx;

    private DbUtil dbUtil;

    public void init() throws Exception {
        utx.begin();
        dbUtil = new DbUtil(dataSource);
        dbUtil.clearTables();
        utx.commit();
    }

    @Test
    public void testNewTodo() throws Exception {
        init();
        utx.begin();
        LOGGER.log(Level.INFO, "new todo ... ");
        Todo todo = Todo.of("test");
        var saved = todos.save(todo);
        utx.commit();

        assertEquals("test", saved.getTitle());

        dbUtil.assertCount("todos", 1);

        var todoGetById = todos.findById(saved.getId());
        assertNotNull(todoGetById);
        assertEquals("test", todoGetById.getTitle());
    }

    @Test
    public void testNewTodo2() throws Exception {
        init();
        utx.begin();
        LOGGER.log(Level.INFO, "new todo2 ... ");
        Todo todo = Todo.of("test");
        var saved = todos.save(todo);
        utx.commit();

        assertEquals("test", saved.getTitle());

        dbUtil.assertCount("todos", 1);

        var getById = entityManager.find(Todo.class, saved.getId());
        assertNotNull(getById);
        assertEquals("test", getById.getTitle());
    }
}
