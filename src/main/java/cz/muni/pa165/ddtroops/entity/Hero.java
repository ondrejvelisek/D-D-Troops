/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.muni.pa165.ddtroops.entity;

import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dufkova
 */
public class Hero {
    
    @Id
    private Long id;
    private String name;
    @ManyToOne
    private Role role;
    private Integer experienceLevel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.role);
        hash = 37 * hash + Objects.hashCode(this.experienceLevel);
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
        final Hero other = (Hero) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.experienceLevel, other.experienceLevel)) {
            return false;
        }
        return true;
    }

  


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getExperienceLevel() {
        return experienceLevel;
    }

    public void setExperienceLevel(Integer experienceLevel) {
        this.experienceLevel = experienceLevel;
    }
    
    
}
