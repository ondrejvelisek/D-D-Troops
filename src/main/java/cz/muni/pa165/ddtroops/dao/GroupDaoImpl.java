/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Group;
import cz.muni.pa165.ddtroops.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
/**
 *
 * @author Peter Strieška
 */
public class GroupDaoImpl implements GroupDao{
    
    @PersistenceContext
    private EntityManager em;
    
    public void setEntityManager(EntityManager em){
        this.em = em;
    }
    
    @Override
    public Long createGroup(Group group)
    {
        em.persist(group);
        return group.getId();
    }
    public Group findGroupByName(String name) {
        try {
            return em
                    .createQuery("select g from Group g where name = :name",
                    Group.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
    public List<Group> findAllGroups() {
        return em.createQuery("select g from Group g", Group.class)
                .getResultList();
    }

    @Override
    public Group getGroupById(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public Group getGroupByName(String name) {
        try{
            return em.createQuery("select g from Group g where name = :name",Group.class).setParameter("name", name).getSingleResult();
        }catch(NoResultException nre){
            return null;
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return em.createQuery("select t from Group t", Group.class)
                .getResultList();
    }

    @Override
    public void updateGroup(Long fromId, Group g) {
        Group from = getGroupById(fromId);
        em.getTransaction().begin();
        from.setId(g.getId());
        from.setName(g.getName());
        em.getTransaction().commit();
    }

    @Override
    public void deleteGroup(Group group) {
        em.remove(group);
    }
    
}
