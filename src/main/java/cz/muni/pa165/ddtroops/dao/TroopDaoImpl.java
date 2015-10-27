/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ciffi
 */
public class TroopDaoImpl implements TroopDao{
    
    @PersistenceContext
    private EntityManager em;

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public Long createTroop(Troop troop) {
        em.persist(troop);
        return troop.getId();
    }

    @Override
    public Troop getTroopById(Long id) {
        return em.find(Troop.class, id);
    }

    @Override
    public Troop getTroopByName(String name) {
        try {
            return em
                    .createQuery("select t from Troop t where name = :name",
                    Troop.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }

    @Override
    public List<Troop> getAllTroops() {
        return em.createQuery("select t from Troop t", Troop.class)
                .getResultList();
    }

    @Override
    public void updateTroop(Long fromId, Troop to) {
        Troop from = getTroopById(fromId);
        em.getTransaction().begin();
        from.setId(to.getId());
        from.setName(to.getName());
        from.setMission(to.getMission());
        from.setGold(to.getGold());
        em.getTransaction().commit();
    }

    @Override
    public void deleteTroop(Troop troop) {
        em.remove(troop);
    }
}
