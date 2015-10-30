/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Group;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Peter Strieška
 */
@Transactional
@Repository
public class GroupDaoImpl implements GroupDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long createGroup(Group group) {
        em.persist(group);
        return group.getId();
    }

    @Override
    public void deleteGroup(Group group) {
        em.remove(group);
    }
    
    @Override
    public void updateGroup(Group group) {
        em.merge(group);
    }

    @Override
    public Group getGroupById(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public Group getGroupByName(String name) {
        try {
            return em
                    .createQuery("select g from HeroGroup g where name = :name",
                    Group.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Group> getAllGroups() {
        return em.createQuery("select g from HeroGroup g", Group.class)
                .getResultList();
    }
}
