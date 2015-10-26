package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Role;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Martin Bajanik
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager em;

    public void setEntityManager(EntityManager manager) {
        this.em = manager;
    }

    /**
     * Returns role according to given id.
     *
     * @param id The id of role to be returned.
     */
    public Role findById(Long id) {
        return em.find(Role.class, id);
    }

    /**
     * Returns all roles.
     *
     */
    public List<Role> findAll() {
        return em.createQuery("select c from Role c", Role.class)
                .getResultList();
    }

    /**
     * Persists the given role.
     *
     * @param role The role to be persisted.
     */
    public void create(Role role) {
        em.persist(role);
    }

    /**
     * Removes given role.
     *
     * @param role The role to be removed.
     */
    public void delete(Role role) {
        em.remove(role);
    }

    /**
     * Returns role with given name.
     *
     * @param name The name of role to be returned.
     */
    public Role findByName(String name) {
        try {
            return em
                    .createQuery("select c from Role c where name = :name",
                    Role.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
}
