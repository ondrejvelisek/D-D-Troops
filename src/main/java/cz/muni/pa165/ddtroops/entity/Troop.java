/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.entity;

import java.math.BigDecimal;
import java.util.Objects;
import javax.persistence.Id;

/**
 *
 * @author ciffi
 */
public class Troop {
    
    @Id
    private Long id;
    private String name;
    private String mission;
    private BigDecimal gold;

    public Troop(Long id, String name, String mission, BigDecimal gold) {
        this.id = id;
        this.name = name;
        this.mission = mission;
        this.gold = gold;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMission() {
        return mission;
    }

    public void setMission(String mission) {
        this.mission = mission;
    }

    public BigDecimal getGold() {
        return gold;
    }

    public void setGold(BigDecimal gold) {
        this.gold = gold;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        hash = 71 * hash + Objects.hashCode(this.name);
        hash = 71 * hash + Objects.hashCode(this.mission);
        hash = 71 * hash + Objects.hashCode(this.gold);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Troop other = (Troop) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.mission, other.mission)) {
            return false;
        }
        if (!Objects.equals(this.gold, other.gold)) {
            return false;
        }
        return true;
    }    
        
}
