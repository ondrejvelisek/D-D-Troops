package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Role;
import cz.muni.pa165.ddtroops.enums.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
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

    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CreateRoleWithNullName() {
        Role role = new Role(null, Resource.MANA, "decs");
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CreateRoleWithNullResource() {
        Role role = new Role("name", null, "decs");
    }

    @Test
    public void createRole() {
        Role role = new Role("name1", Resource.MANA, "desc");
        List<Role> roles = dao.findAll();
        Assert.assertEquals(roles.size(), 0);
        dao.create(role);
        List<Role> rolesAfterCreate = dao.findAll();
        Assert.assertEquals(rolesAfterCreate.size(), 1);
    }
    
    @Test
    public void findRoleById() {
        Role role = new Role("name", Resource.MANA, "desc");
        dao.create(role);
        Role roleFromDb = dao.findByName(role.getName());
        role.setId(roleFromDb.getId());
        Assert.assertEquals(role, roleFromDb);        
    }
    
    @Test
    public void findRoleByName() {
        String name = "name";
        Role role = new Role("name", Resource.RUNIC_POWER, "desc");
        dao.create(role);
        Role roleFromDb = dao.findByName(name);
        role.setId(roleFromDb.getId());
        Assert.assertEquals(role, roleFromDb);
    }
    
    @Test
    public void findAllRoles() {
        Role role1 = new Role("name1", Resource.MANA, "desc");
        Role role2 = new Role("name2", Resource.ENERGY, "desc");
        
        dao.create(role1);
        dao.create(role2);
        
        List<Role> roles = dao.findAll();
        
        Assert.assertEquals(roles.size(), 2);
    }
    
    @Test
    public void deleteRole() {
        Role role = new Role("name1", Resource.MANA, "desc");
        dao.create(role);
        Role roleFromDb = dao.findByName(role.getName());
        Long id = roleFromDb.getId();
        
        assertNotNull(dao.findById(id));
        dao.delete(roleFromDb);
        assertNull(dao.findById(id));
    }
}
