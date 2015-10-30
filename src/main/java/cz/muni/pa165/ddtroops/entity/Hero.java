package cz.muni.pa165.ddtroops.entity;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Dufkova
 */
@Entity
public class Hero {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    private Integer experienceLevel;
    @ManyToMany
    private Set<Group> groups = new HashSet<Group>();
    @ManyToMany
    private Set<Role> roles = new HashSet<Role>();
    @ManyToOne
    private Troop troop;

    public Set<Group> getGroups() {
        return groups;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public Troop getTroop() {
        return troop;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.name);
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

    public boolean assignToTroop(Troop troop, boolean force) {
        if (this.troop == null || force) {
            this.troop = troop;
            return true;
        }

        return false;
    }

    public void kickFromTroop() {
        this.troop = null;
    }

    public void addToRole(Role role) {
        this.roles.add(role);
    }

    public void removeFromRole(Role role) {
        if (!this.roles.contains(role)) {
            throw new NoSuchElementException("No role: " + role.toString());
        }

        this.roles.remove(role);
    }
}
