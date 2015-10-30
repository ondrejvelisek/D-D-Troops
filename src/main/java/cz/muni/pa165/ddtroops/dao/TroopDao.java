package cz.muni.pa165.ddtroops.dao;

import cz.muni.pa165.ddtroops.entity.Troop;
import java.util.List;

/**
 * TroopDao performs CRUD operations on entity Troop
 *
 * @author Filip Ciffary
 */
public interface TroopDao {
    /*
     * Persists troop
     * 
     * @param troop troop to be persisted
     */
    public Long createTroop(Troop troop);
    
    /**
     * Returns troop by id
     * @param id of required troop
     * @return Required troop by id
     */
    public Troop getTroopById(Long id);
    
    /**
     * Returns troop by name
     * @param name of required troop
     * @return Required troop by name
     */
    public Troop getTroopByName(String name);
    
    /**
     * Returns all troops
     * @return List of all troops
     */
    public List<Troop> getAllTroops();
    
    /**
     * Updates specific troop
     * @param troop Troop to be updated
     */
    public void updateTroop(Troop troop);
    
    /**
     * Removes specific troop
     * @param troop Troop to be removed
     */
    public void deleteTroop(Troop troop);
}
