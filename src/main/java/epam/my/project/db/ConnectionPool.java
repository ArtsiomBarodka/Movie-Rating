package epam.my.project.db;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    boolean releaseConnection(Connection connection);

    void shutdown();
}
