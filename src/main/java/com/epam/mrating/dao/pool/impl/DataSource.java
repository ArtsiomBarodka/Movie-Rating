package com.epam.mrating.dao.pool.impl;

import static org.apache.logging.log4j.LogManager.getLogger;

import com.epam.mrating.configuration.ResourceConfiguration;
import com.epam.mrating.dao.exception.ConnectionPoolException;
import com.epam.mrating.dao.pool.ConnectionPool;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;


/**
 * The enum Data source.
 *
 * @author Artsiom Borodko
 * @see https://github.com/ArtsiomBarodka/Movie-Rating
 */
public enum DataSource implements ConnectionPool {
    /**
     * Connection pool instance data source.
     */
    CONNECTION_POOL_INSTANCE;

    private final Logger logger = getLogger(DataSource.class);

    private ReentrantLock locker = new ReentrantLock();
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<>(ResourceConfiguration.CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private Set<Connection> takenConnections = new HashSet<>();

    DataSource() {
        initConnections();
    }

    public Connection getConnection() {
        Connection connection;
            try {
                connection = availableConnections.poll(ResourceConfiguration.CONFIGURATION_INSTANCE.getDbMaxWaitTimeout(), TimeUnit.SECONDS);
                locker.lock();
                if(Objects.isNull(connection)){
                    logger.warn("Can`t get connection to database. Connection is null");
                    throw new ConnectionPoolException("Can`t get connection to database");
                }
                takenConnections.add(connection);
            } catch (Exception e) {
                logger.warn(String.format("Trying to take connection was failed: %s", e.getMessage()), e);
                throw new ConnectionPoolException("Trying to take connection was failed: " + e.getMessage(), e);
            } finally {
                locker.unlock();
            }
        return connection;
    }

    public void returnConnection(Connection connection) {
        try {
            locker.lock();
            if(takenConnections.remove(connection)){
                availableConnections.put(connection);
            } else {
                logger.warn("Trying to release connection was failed");
            }
        } catch (InterruptedException e) {
            logger.warn(String.format("Trying to close connection from taken connections was failed: %s", e.getMessage()), e);
            throw new ConnectionPoolException("Trying to close connection from taken connections was failed", e);
        } finally {
            locker.unlock();
        }
    }

    public void shutdown() {
        try {
            locker.lock();
            closeAllConnectionsOfCollection(takenConnections);
            closeAllConnectionsOfCollection(availableConnections);
            takenConnections.clear();
            availableConnections.clear();
        } finally {
            locker.unlock();
            logger.info("shutdown of connections was done");
        }
    }

    private void closeAllConnectionsOfCollection(Iterable iterable){
        for (Object object : iterable){
            Connection$Proxy connection = (Connection$Proxy) object;
            try {
                connection.shutdown();
            } catch (SQLException e) {
                logger.error(String.format("Trying to close connection from taken connections was failed: %s", e.getMessage()), e);
                throw new ConnectionPoolException("Trying to take connection was interrupted: " + e.getMessage(), e);
            }
        }
    }

    private void initConnections() {
        createConnections(ResourceConfiguration.CONFIGURATION_INSTANCE.getDbInitialPoolSize());
        logger.info("initialization of connections was done");
    }

    private void createConnections(int valueOfConnections) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            for (int i = 0; i < valueOfConnections; i++) {
                Connection connection = new Connection$Proxy(DriverManager.getConnection(
                        ResourceConfiguration.CONFIGURATION_INSTANCE.getDbUrl(),
                        ResourceConfiguration.CONFIGURATION_INSTANCE.getDbUser(),
                        ResourceConfiguration.CONFIGURATION_INSTANCE.getDbPassword()));

                availableConnections.add(connection);
            }
        } catch (Exception e) {
            logger.error("Trying to create connection was failed:" + e.getMessage(), e);
            throw new ConnectionPoolException("Can`t create connection to database: "+ e.getMessage(), e);
        }
    }
}
