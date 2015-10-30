package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Hero;
import java.util.List;

/**
 *
 * @author Dufkova
 */
public interface HeroDao {
    /**
     * Stores given hero to DB.
     * 
     * @param hero Hero to store.
     */
    public Long createHero(Hero hero);
    
    /**
     * Returns all heros from DB.
     * 
     */
    public List<Hero> getAllHeroes();
    
    /**
     * Returns hero from DB by ID.
     * 
     * @param id Id to be searched.
     */
    public Hero getHeroById(Long id);
    
    /**
     * Returns hero from DB by name.
     * 
     * @param name name to be searched.
     */
    public Hero getHeroByName(String name);
    
    /**
     * Updates hero in DB.
     * 
     * @param hero hero to be updated.
     */
    public void updateHero(Hero hero);
    
    /**
     * Deletes hero from DB
     * 
     * @param hero Hero to be deleted.
     */
    public void deleteHero(Hero hero);
}
