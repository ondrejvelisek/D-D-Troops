package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Troop;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of TroopDao interface
 * 
 * @author Filip Ciffary
 */
@Transactional
@Repository
public class TroopDaoImpl implements TroopDao{
    
    @PersistenceContext
    private EntityManager em;

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
    public void updateTroop(Troop troop) {
        em.merge(troop);
    }

    @Override
    public void deleteTroop(Troop troop) {
        em.remove(troop);
    }
}
