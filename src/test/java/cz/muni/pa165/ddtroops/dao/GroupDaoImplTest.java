package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Group;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Strieška
 **/
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class GroupDaoImplTest {
    
    private @Autowired
    GroupDao GroupDao;
    private @PersistenceContext
    EntityManager em;

    @Test(expected = IllegalArgumentException.class)
    public void CreateGroupWithNullResource() {
        GroupDao.createGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateGroupWithNullResource() {
        GroupDao.updateGroup(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeletGroupWithNullResource() {
        GroupDao.deleteGroup(null);
    }

    @Test
    public void createGroup() {
        Group group = new Group("Group name");

        GroupDao.createGroup(group);

        assertThat(em.find(Group.class, group.getId())).isNotNull();
    }

    @Test
    public void deleteGroup() {
        Group group = new Group("Group name");

        em.persist(group);

        GroupDao.deleteGroup(group);
        assertThat(em.find(Group.class, group.getId())).isNull();
    }

    @Test
    public void updateGroup() {
        Group group = new Group("Original name");
        em.persist(group);

        group.setName("Updated name");
        GroupDao.updateGroup(group);

        Group fromDb = em.find(Group.class, group.getId());

        assertThat(fromDb.getName()).isEqualTo("Updated name");
    }

    @Test
    public void getGroupById() {
        Group group = new Group("Name");

        em.persist(group);

        Group fromDb = GroupDao.getGroupById(group.getId());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void getGroupByName() {
        Group group = new Group("Name");
        em.persist(group);

        Group fromDb = GroupDao.getGroupByName(group.getName());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void getAllGroups() {
        Group group1 = new Group("Name1");
        Group group2 = new Group("Name2");

        em.persist(group1);
        em.persist(group2);

        List<Group> groups = GroupDao.getAllGroups();

        assertThat(groups)
                .isNotNull()
                .hasOnlyElementsOfType(Group.class)
                .hasSize(2)
                .withFailMessage("Incorrect number of groups returned.");
    }
}