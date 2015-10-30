package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Hero;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Dufkova
 */
@Transactional
@Repository
public class HeroDaoImpl implements HeroDao{

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public Long createHero(Hero hero) {
        em.persist(hero);
        return hero.getId();
    }

    @Override
    public List<Hero> getAllHeroes() {
        return em.createQuery("select c from Hero c", Hero.class)
                .getResultList();
    }

    @Override
    public Hero getHeroById(Long id) {
        return em.find(Hero.class, id);
    }

    @Override
    public void updateHero(Hero hero) {
        em.merge(hero);
    }

    @Override
    public void deleteHero(Hero hero) {
       em.remove(hero);
    }

    public Hero getHeroByName(String name) {
        try {
            return em
                    .createQuery("select c from Hero c where name = :name",
                    Hero.class).setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException nrf) {
            return null;
        }
    }
    
    
}
