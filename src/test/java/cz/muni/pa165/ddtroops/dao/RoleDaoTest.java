package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Role;
import cz.muni.pa165.ddtroops.enums.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Bajanik
 * @author 
 */
@ContextConfiguration(classes = {PersistenceTestApplicationContext.class})
public class RoleDaoTest extends AbstractTestNGSpringContextTests {

    private RoleDao dao;
    private EntityManager manager;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @BeforeMethod
    public void setUp() {
        dao = new RoleDaoImpl();
        manager = emf.createEntityManager();
        dao.setEntityManager(manager);
    }

    @AfterMethod
    public void tearDown() {
        manager.close();
    }

    @Test
    public void createRoleWithoutRequiredParametersFails() {
        try {
            Role role = new Role(null, Resource.MANA, "");
            fail("Creating role without name did not fail.");
        } catch (IllegalArgumentException ex) {
        }

        try {
            Role role = new Role("Name", null, "");
            fail("Creating role without resource type did not fail.");
        } catch (IllegalArgumentException ex) {
        }
    }

    @Test
    public void createRole() {
        
    }
}
