package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Group;
import java.util.List;

/**
 *
 * @author Peter Strieška
 */
public interface GroupDao {
    /**
     * Stores given group to DB.
     * 
     * @param group Group to store.
     */
    public Long createGroup(Group group);
    
    /**
     * Returns group from DB by ID.
     * 
     * @param id Id to be searched.
     */
    public Group getGroupById(Long id);
    
    /**
     * Returns group from DB by name.
     * 
     * @param name name to be searched.
     */
    public Group getGroupByName(String name);
    
    /**
     * Returns all groups from DB.
     * 
     */
    public List<Group> getAllGroups();
    
    /**
     * Updates group in DB.
     * 
     * @param group group to be updated.
     */
    public void updateGroup(Group group);
    
    /**
     * Deletes group from DB
     * 
     * @param group Group to be deleted.
     */
    public void deleteGroup(Group group);
}

