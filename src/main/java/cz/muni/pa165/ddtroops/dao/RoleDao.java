package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Role;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Martin Bajanik
 */
public interface RoleDao {

    public Role findById(Long id);
    public void create(Role role);
    public void delete(Role role);
    public List<Role> findAll();
    public Role findByName(String name);
    public void setEntityManager(EntityManager manager);
}
