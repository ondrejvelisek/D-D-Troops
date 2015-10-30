package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Troop;
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
 * @author Dufkova
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PersistenceTestApplicationContext.class)
public class TroopDaoImplTest {

    private @Autowired
    TroopDao TroopDao;
    private @PersistenceContext
    EntityManager em;

    @Test(expected = IllegalArgumentException.class)
    public void CreateTroopWithNullResource() {
        TroopDao.createTroop(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void UpdateTroopWithNullResource() {
        TroopDao.updateTroop(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void DeletTroopWithNullResource() {
        TroopDao.deleteTroop(null);
    }

    @Test
    public void createTroop() {
        Troop troop = new Troop("Name", "Collect all the flowers", new BigDecimal("10"));

        TroopDao.createTroop(troop);
        assertThat(em.find(Troop.class, troop.getId())).isNotNull();
    }

    @Test
    public void deleteTroop() {
        Troop troop = new Troop("Name", "Collect all the flowers", new BigDecimal("10"));

        em.persist(troop);

        TroopDao.deleteTroop(troop);
        assertThat(em.find(Troop.class, troop.getId())).isNull();
    }

    @Test
    public void updateTroop() {
        Troop troop = new Troop("Name", "Collect all the flowers", new BigDecimal("10"));
        em.persist(troop);

        troop.setName("Updated name");
        troop.setMission("Sell all the flowers");
        troop.setGold(new BigDecimal("12"));

        TroopDao.updateTroop(troop);

        Troop fromDb = em.find(Troop.class, troop.getId());

        assertThat(fromDb.getName()).isEqualTo("Updated name");
        assertThat(fromDb.getMission()).isEqualTo("Sell all the flowers");
        assertThat(fromDb.getGold()).isEqualTo(new BigDecimal("12"));
    }

    @Test
    public void findTroopById() {
        Troop troop = new Troop("Name", "Collect all the flowers", new BigDecimal("10"));

        em.persist(troop);

        Troop fromDb = TroopDao.getTroopById(troop.getId());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findTroopByName() {
        Troop troop = new Troop("Name", "Collect all the flowers", new BigDecimal("10"));
        em.persist(troop);

        Troop fromDb = TroopDao.getTroopByName(troop.getName());
        assertThat(fromDb).isNotNull();
    }

    @Test
    public void findAllTroops() {
        Troop troop1 = new Troop("Name1", "Collect all the flowers", new BigDecimal("10"));
        Troop troop2 = new Troop("Name2", "Sell all the flowers", new BigDecimal("1"));

        em.persist(troop1);
        em.persist(troop2);

        List<Troop> troops = TroopDao.getAllTroops();

        assertThat(troops)
                .isNotNull()
                .hasOnlyElementsOfType(Troop.class)
                .hasSize(2)
                .withFailMessage("Incorrect number of troops returned.");
    }
}
