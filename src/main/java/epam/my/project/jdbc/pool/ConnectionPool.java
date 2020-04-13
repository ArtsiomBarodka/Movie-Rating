package epam.my.project.jdbc.pool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    void returnConnection(Connection connection);

    void shutdown();
}
