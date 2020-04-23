package epam.my.project.dao.jdbc.handler;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public final class InsertParametersHandler {
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
