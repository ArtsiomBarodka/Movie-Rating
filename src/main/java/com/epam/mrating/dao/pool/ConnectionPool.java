package com.epam.mrating.dao.pool;

import java.sql.Connection;

/**
 * The interface Connection pool.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
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
