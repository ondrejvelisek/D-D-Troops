package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Role;
import cz.muni.pa165.ddtroops.enums.Resource;
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
 * @author Filip Ciffary
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class RoleDaoImplTest {

    private @Autowired
    RoleDao RoleDao;
    private @PersistenceContext
    EntityManager em;

    @Test(expected = IllegalArgumentException.class)
    public void CreateRoleWithNullResource() {
        RoleDao.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateRoleWithNullResource() {
        RoleDao.update(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeletRoleWithNullResource() {
        RoleDao.delete(null);
    }

    @Test
    public void createRole() {
        Role role = new Role("Name", Resource.MANA, "Description");

        RoleDao.create(role);

        assertThat(em.find(Role.class, role.getId())).isNotNull();
    }

    @Test
    public void deleteRole() {
        Role role = new Role("Name", Resource.MANA, "Description");

        em.persist(role);

        RoleDao.delete(role);
        assertThat(em.find(Role.class, role.getId())).isNull();
    }

    @Test
    public void updateRole() {
        Role role = new Role("Otiginal name", Resource.MANA, "desc");
        em.persist(role);

        role.setName("Updated name");
        role.setDescription("Updated description");
        role.setResource(Resource.RAGE);

        RoleDao.update(role);

        Role fromDb = em.find(Role.class, role.getId());

        assertThat(fromDb.getName()).isEqualTo("Updated name");
        assertThat(fromDb.getResource()).isEqualTo(Resource.RAGE);
        assertThat(fromDb.getDescription()).isEqualTo("Updated description");
    }

    @Test
    public void findRoleById() {
        Role role = new Role("Name", Resource.MANA, "Description");

        em.persist(role);

        Role fromDb = RoleDao.findById(role.getId());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findRoleByName() {
        Role role = new Role("Name", Resource.MANA, "Description");
        em.persist(role);

        Role fromDb = RoleDao.findByName(role.getName());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findAllRoles() {
        Role role1 = new Role("Name1", Resource.MANA, "desc");
        Role role2 = new Role("Name2", Resource.ENERGY, "desc");

        em.persist(role1);
        em.persist(role2);

        List<Role> roles = RoleDao.findAll();

        assertThat(roles)
                .isNotNull()
                .hasOnlyElementsOfType(Role.class)
                .hasSize(2)
                .withFailMessage("Incorrect number of roles returned.");
    }
}
