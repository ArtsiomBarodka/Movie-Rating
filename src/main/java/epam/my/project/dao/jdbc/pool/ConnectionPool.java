package epam.my.project.dao.jdbc.pool;

import java.sql.Connection;

/**
 * The interface Connection pool.
 */
public interface ConnectionPool {
    /**
     * Gets connection.
     *
     * @return the connection
     */
    Connection getConnection();

    /**
     * Return connection.
     *
     * @param connection the connection
     */
    void returnConnection(Connection connection);

    /**
     * Shutdown.
     */
    void shutdown();
}
