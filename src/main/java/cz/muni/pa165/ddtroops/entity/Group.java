package cz.muni.pa165.ddtroops.entity;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Peter Strieška
 */
@Entity(name = "HeroGroup") // Group is reserved keyword in database
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    @ManyToMany
    private Set<Hero> heroes = new HashSet<Hero>();

    public Group() {
    }

    public Group(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Invalid Group name. Name is null");
        }
        this.name = name;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Group)) {
            return false;
        }
        final Group other = (Group) obj;
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Group{"
                + "id=" + id
                + ", name=" + name + '}';
    }

    public void addHero(Hero hero) {
        this.heroes.add(hero);
    }

    public void removeHero(Hero hero) {
        if (!this.heroes.contains(hero)) {
            throw new NoSuchElementException("No hero: " + hero.toString());
        }

        this.heroes.remove(hero);
    }
}
