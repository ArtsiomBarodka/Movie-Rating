package epam.my.project.model.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Abstract entity.
 *
 * @param <T> the type parameter
 */
public abstract class AbstractEntity<T> implements Serializable {
    private static final long serialVersionUID = 1647836968413134701L;

    /**
     * The Id.
     */
    T id;

    /**
     * Gets id.
     *
     * @return the id
     */
    public T getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(T id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AbstractEntity other = (AbstractEntity) obj;
        if (id == null) {
            return other.id == null;
        } else {
            return id.equals(other.id);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
