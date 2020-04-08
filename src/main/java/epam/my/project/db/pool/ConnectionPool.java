package epam.my.project.db.pool;

import java.sql.Connection;

public interface ConnectionPool {
    Connection getConnection();

    void releaseConnection(Connection connection);

    void shutdown();
}
