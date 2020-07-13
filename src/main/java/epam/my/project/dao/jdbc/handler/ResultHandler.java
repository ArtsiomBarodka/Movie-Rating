package epam.my.project.dao.jdbc.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface ResultHandler<T> {
    T handle(ResultSet rs) throws SQLException;
}
