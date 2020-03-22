package epam.my.project.db.handler.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertParametersHandler {
    public static void handle(PreparedStatement ps, Object... parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
        }
    }
}
