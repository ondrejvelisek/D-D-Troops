/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.d.d.troops;

import java.util.List;

/**
 *
 * @author ciffi
 */
public interface TroopManager {
    public Long createTroop(Troop troop);
    public Troop getTroopById(Long id);
    public List<Troop> getAllTroops();
    public void updateTroop(Troop troop);
    public void deleteTroop(Troop troop);
}
