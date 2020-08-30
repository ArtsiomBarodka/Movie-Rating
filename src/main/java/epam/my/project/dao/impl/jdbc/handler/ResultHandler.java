package epam.my.project.dao.impl.jdbc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The interface Result handler.
 *
 * @param <T> the type parameter
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
@FunctionalInterface
public interface ResultHandler<T> {
    /**
     * Handle t.
     *
     * @param rs the rs
     * @return the t
     * @throws SQLException the sql exception
     */
    T handle(ResultSet rs) throws SQLException;
}
