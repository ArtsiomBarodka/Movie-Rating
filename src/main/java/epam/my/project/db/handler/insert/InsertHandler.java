package epam.my.project.db.handler.insert;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertHandler {
    public static void insertParameters(PreparedStatement ps, Object... parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                ps.setObject(i + 1, parameters[i]);
            }
        }
    }
}
