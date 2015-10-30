package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Hero;
import cz.muni.pa165.ddtroops.entity.Role;
import cz.muni.pa165.ddtroops.entity.Troop;
import cz.muni.pa165.ddtroops.enums.Resource;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Martin Bajanik
 **/
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class HeroDaoImplTest {

    private @Autowired
    HeroDao HeroDao;
    private @PersistenceContext
    EntityManager em;

    @Test(expected = IllegalArgumentException.class)
    public void CreateHeroWithNullResource() {
        HeroDao.createHero(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateHeroWithNullResource() {
        HeroDao.updateHero(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeletHeroWithNullResource() {
        HeroDao.deleteHero(null);
    }

    @Test
    public void createHero() {
        Hero hero = new Hero();
        hero.setName("Adamek");

        HeroDao.createHero(hero);
        assertThat(em.find(Hero.class, hero.getId())).isNotNull();
    }

    @Test
    public void deleteHero() {
        Hero hero = new Hero();
        hero.setName("Adamek");

        em.persist(hero);

        HeroDao.deleteHero(hero);
        assertThat(em.find(Hero.class, hero.getId())).isNull();
    }

    @Test
    public void updateHero() {
        Hero hero = new Hero();
        hero.setName("Adamek");
        em.persist(hero);

        hero.setName("Updated name");
        hero.setExperienceLevel(12);

        HeroDao.updateHero(hero);

        Hero fromDb = em.find(Hero.class, hero.getId());

        assertThat(fromDb.getName()).isEqualTo("Updated name");
        assertThat(fromDb.getExperienceLevel()).isEqualTo(12);
    }

    @Test
    public void findHeroById() {
        Hero hero = new Hero();
        hero.setName("Adamek");

        em.persist(hero);

        Hero fromDb = HeroDao.getHeroById(hero.getId());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findHeroByName() {
        Hero hero = new Hero();
        hero.setName("Adamek");
        em.persist(hero);

        Hero fromDb = HeroDao.getHeroByName(hero.getName());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findAllHeros() {
        Hero hero1 = new Hero();
        hero1.setName("Adamek");
        Hero hero2 = new Hero();
        hero2.setName("Bajanik");

        em.persist(hero1);
        em.persist(hero2);

        List<Hero> heros = HeroDao.getAllHeroes();

        assertThat(heros)
                .isNotNull()
                .hasOnlyElementsOfType(Hero.class)
                .hasSize(2)
                .withFailMessage("Incorrect number of heroes returned.");
    }
    
    @Test
    public void addHeroToTroop() {
        Hero hero = new Hero();
        hero.setName("Bajanik");
        
        Troop troop = new Troop("Best troop", "Pass Java 3", BigDecimal.ZERO);
        em.persist(troop);
        
        hero.assignToTroop(troop, true);      
        em.persist(hero);
        
        assertThat(hero.getTroop()).isNotNull();
        assertThat(hero.getTroop().getName()).isEqualTo("Best troop");
        
        Troop troop1 = new Troop("Not the best troop", "Fail Java 3", BigDecimal.ZERO);
        
        hero.assignToTroop(troop1, false);      
        em.persist(hero);
        
        assertThat(hero.getTroop()).isNotNull();
        assertThat(hero.getTroop().getName()).isEqualTo("Best troop");
        
        hero.assignToTroop(troop1, true);      
        em.persist(hero);
        
        assertThat(hero.getTroop()).isNotNull();
        assertThat(hero.getTroop().getName()).isEqualTo("Not the best troop");           
    }
}
