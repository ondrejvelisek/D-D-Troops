/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.PersistenceTestApplicationContext;
import cz.muni.pa165.ddtroops.entity.Troop;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;


import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.Test;

/**
 *
 * @author Dufkova
 */
@ContextConfiguration(classes = {PersistenceTestApplicationContext.class})
public class TroopDaoImplTest extends AbstractTestNGSpringContextTests{
  
  @PersistenceUnit
  private EntityManagerFactory emf;
  //private EntityManager em;
  private TroopDaoImpl dao;
  


  

  /**
   * Test of createTroop method, of class TroopDaoImpl.
   */
  @Test
  public void testCreateTroop() {
  EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
    System.out.println("createTroop");
    
    Troop troop = new Troop("mytroo","misson",new BigDecimal(7));
    Troop troopExpected = new Troop("mytroo","misson",new BigDecimal(7));
    em.getTransaction().begin();
    em.persist(troop);
    em.getTransaction().commit();
    Long expResult = dao.createTroop(troop);
    Long result = dao.createTroop(troopExpected);
    assertEquals((long)expResult,(long)(result+1));
    Troop notInDb = new Troop("mytroo","misson",new BigDecimal(7));
    troop = dao.getTroopById(expResult);
    assertEquals(troop, notInDb);
  }

  /**
   * Test of getTroopById method, of class TroopDaoImpl.
   */
  @Test
  public void testGetTroopById() {
    EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
    Long id = null;
    Troop expResult = new Troop("mytroo","misson",new BigDecimal(7));
    id = dao.createTroop(expResult);
    expResult.setId(id);
    Troop result = dao.getTroopById(id);
    assertEquals(expResult, result);
    
  }

  /**
   * Test of getTroopByName method, of class TroopDaoImpl.
   */
  @Test
  public void testGetTroopByName() {
    EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
    System.out.println("getTroopByName");
    String name = "name";
    Troop expResult =  new Troop("name","misson",new BigDecimal(7));
    Troop result = dao.getTroopByName(name);
    expResult.setId(dao.createTroop(result));
    assertEquals(expResult, result);
    
  }

  /**
   * Test of getAllTroops method, of class TroopDaoImpl.
   */
  @Test
  public void testGetAllTroops() {
    EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
    System.out.println("getAllTroops");
    Troop expResult =  new Troop("name","misson",new BigDecimal(7));
    Troop expResult2 =  new Troop("name","misson",new BigDecimal(7));
    dao.createTroop(expResult);
    dao.createTroop(expResult2);
    List<Troop> list = dao.getAllTroops();
    assertEquals(list.size(), 2);
    
  }

  /**
   * Test of updateTroop method, of class TroopDaoImpl.
   */
  @Test
  public void testUpdateTroop() {
    EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
    System.out.println("updateTroop");
    Long fromId = null;
    Troop troop = new Troop("name","misson",new BigDecimal(7));
    Troop expectedTroop = new Troop("update","misson",new BigDecimal(7));
    expectedTroop.setId(dao.createTroop(troop));
    troop.setName("update");
    dao.updateTroop(troop.getId(), troop);
    assertEquals(expectedTroop, troop);
  }

  /**
   * Test of deleteTroop method, of class TroopDaoImpl.
   */
  @Test
  public void testDeleteTroop() {
    EntityManager em = emf.createEntityManager();
  dao = new TroopDaoImpl();
  dao.setEm(em);
     System.out.println("getAllTroops");
    Troop expResult =  new Troop("name","misson",new BigDecimal(7));
    Troop expResult2 =  new Troop("name","misson",new BigDecimal(7));
    dao.createTroop(expResult);
    dao.createTroop(expResult2);
    List<Troop> list = dao.getAllTroops();
    assertEquals(list.size(), 2);
    dao.deleteTroop(expResult);
    assertEquals(list.size(), 1);
  }
  
}
