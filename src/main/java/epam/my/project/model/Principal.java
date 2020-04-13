package epam.my.project.model;

import java.io.Serializable;
import java.util.Objects;

public class Principal implements Serializable {
    private static final long serialVersionUID = 4922847827424229169L;

    private String role;
    private String name;
    private int id;

    public Principal() {
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean hasRole(String role){
        if(role == null){
            return false;
        }
        return this.role.equals(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Principal principal = (Principal) o;
        return id == principal.id &&
                Objects.equals(role, principal.role) &&
                Objects.equals(name, principal.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, name, id);
    }

    @Override
    public String toString() {
        return "Principal{" +
                "role='" + role + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
