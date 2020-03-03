package epam.my.project.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class AbstractEntity<T> implements Serializable {
    private static final long serialVersionUID = 1647836968413134701L;

    T id;

    public T getId() {
        return id;
    }

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
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
