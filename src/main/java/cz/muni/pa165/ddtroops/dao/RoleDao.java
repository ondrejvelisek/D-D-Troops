package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Role;
import java.util.List;

/**
 *
 * @author Martin Bajanik
 */
public interface RoleDao {
    
    /**
     * Stores given role to DB.
     * 
     * @param role Role to store.
     */
    public void create(Role role);
    
    /**
     * Deletes role from DB
     * 
     * @param role Role to be deleted.
     */
    public void delete(Role role);
    
    /**
     * Updates role in DB.
     * 
     * @param role Role to be updated.
     */
    public void update(Role role);
    
    /**
     * Returns all roles from DB.
     * 
     */
    public List<Role> findAll();
    
    /**
     * Returns role from DB by name.
     * 
     * @param name name to be searched.
     */
    public Role findByName(String name);
    
    /**
     * Returns role from DB by id.
     * 
     * @param id id to be searched.
     */
    public Role findById(Long id);
}
