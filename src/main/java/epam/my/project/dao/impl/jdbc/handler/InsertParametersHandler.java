package epam.my.project.dao.impl.jdbc.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The type Insert parameters handler.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public final class InsertParametersHandler {
    /**
     * Handle.
     *
     * @param ps         the ps
     * @param parameters the parameters
     * @throws SQLException the sql exception
     */
    public static void handle(PreparedStatement ps, Object... parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
        }
    }

    private InsertParametersHandler(){

    }
}
