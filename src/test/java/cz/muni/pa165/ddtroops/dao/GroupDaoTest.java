package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Group;
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
 * @author Peter Strieška
 */
@ContextConfiguration(classes = {PersistenceTestApplicationContext.class})
public class GroupDaoTest extends AbstractTestNGSpringContextTests{
    
    private GroupDao dao;
    private EntityManager manager;
    @PersistenceUnit
    private EntityManagerFactory emf;

    @BeforeMethod
    public void setUp() {
        dao = new GroupDaoImpl();
        manager = emf.createEntityManager();
        dao.setEntityManager(manager);
    }

    @AfterMethod
    public void tearDown() {
        manager.close();
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void CreateGroupWithNullName() {
        Group proup = new Group(null);
    }
    

    @Test
    public void createRole() {
        Group group = new Group("Group_01");
        List<Group> groups = dao.findAll();
        Assert.assertEquals(groups.size(), 0);
        dao.create(group);
        List<Group> groupsAfterCreate = dao.findAll();
        Assert.assertEquals(groupsAfterCreate.size(), 1);
    }
    
    @Test
    public void findRoleById() {
        Group group = new Group("Group_01");
        dao.create(group);
        Group groupFromDb = dao.findByName(group.getName());
        group.setId(groupFromDb.getId());
        Assert.assertEquals(group, groupFromDb);        
    }
    
    @Test
    public void findGroupByName() {
        String name = "Name";
        Group group = new Group("Group_01");
        dao.createGroup(group);
        Group groupFromDb = dao.findGroupByName(name);
        group.setId(groupFromDb.getId());
        Assert.assertEquals(group, groupFromDb);
    }
    
    @Test
    public void findAllGroups() {
        Group group1 = new Group("Group_01");
        Group group2 = new Group("Group_02");
        
        dao.createGroup(group1);
        dao.createGroup(group2);
        
        List<Group> groups = dao.findAllGroups();
        
        Assert.assertEquals(groups.size(), 2);
    }
    
    @Test
    public void deleteGroup() {
        Group group = new Group("Group_01");
        dao.createGroup(group);
        Group groupFromDb = dao.findGroupByName(group.getName());
        Long id = groupFromDb.getId();
        
        assertNotNull(dao.findGroupById(id));
        dao.deleteGroup(groupFromDb);
        assertNull(dao.findGroupById(id));
    }
    
}
