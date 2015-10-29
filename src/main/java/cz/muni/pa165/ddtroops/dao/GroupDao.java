/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Group;
import java.util.List;

/**
 *
 * @author Peter Strieška
 */
public interface GroupDao {
    public Long createGroup(Group group);
    public Group getGroupById(Long id);
    public Group getGroupByName(String name);
    public List<Group> getAllGroups();
    public void updateGroup(Long fromId, Group to);
    public void deleteGroup(Group troop);
}

