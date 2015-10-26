package cz.muni.pa165.ddtroops.entity;

import cz.muni.pa165.ddtroops.enums.Resource;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Role contains name, description and other suitable information. Example of a
 * role is "elf magician".
 *
 * @author Martin Bajanik
 */
@Entity
public class Role implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
    @NotNull
    @Column(nullable = false)
    private Resource resource;
    private String description;

    public Role() {
    }

    public Role(String name, Resource resource, String description) {
        if (name == null) {
            throw new IllegalArgumentException("name");
        }
        if (resource == null) {
            throw new IllegalArgumentException("resource");
        }

        this.name = name;
        this.resource = resource;
        this.description = description;
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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Role: "
                + "name = " + name
                + ", resource = " + resource
                + ", description = " + description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Role)) {
            return false;
        }
        final Role other = (Role) obj;
        if (name == null) {
            if (other.getName() != null) {
                return false;
            }
        } else if (!name.equals(other.getName())) {
            return false;
        }
        return true;
    }
}
