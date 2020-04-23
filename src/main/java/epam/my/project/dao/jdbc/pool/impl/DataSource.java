package epam.my.project.dao.jdbc.pool.impl;

import static epam.my.project.configuration.ResourceConfiguration.*;
import static org.apache.logging.log4j.LogManager.getLogger;

import epam.my.project.exception.ConnectionPoolException;
import epam.my.project.dao.jdbc.pool.ConnectionPool;
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


public enum DataSource implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private final Logger logger = getLogger(DataSource.class);

    private ReentrantLock locker = new ReentrantLock();
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private Set<Connection> takenConnections = new HashSet<>();

    DataSource() {
        initConnections();
    }

    public Connection getConnection() {
        Connection connection;
            try {
                connection = availableConnections.poll(CONFIGURATION_INSTANCE.getDbMaxWaitTimeout(), TimeUnit.SECONDS);
                locker.lock();
                if(Objects.isNull(connection)){
                    logger.warn("Can`t get connection to database. Connection is null");
                    throw new ConnectionPoolException("Can`t get connection to database");
                }
                takenConnections.add(connection);
            } catch (InterruptedException e) {
                logger.warn("Trying to take connection was interrupted: " + e.getMessage(), e);
                throw new ConnectionPoolException("Trying to take connection was interrupted: " + e.getMessage(), e);
            } finally {
                locker.unlock();
            }
        return connection;
    }

    public void returnConnection(Connection connection) {
        locker.lock();
        releaseConnection(connection);
        locker.unlock();
    }

    public void shutdown() {
        locker.lock();
        closeAllConnectionOfCollection(takenConnections);
        closeAllConnectionOfCollection(availableConnections);
        takenConnections.clear();
        availableConnections.clear();
        locker.unlock();
        logger.info("shutdown of connections was done");
    }

    private void closeAllConnectionOfCollection(Iterable iterable){
        for (Object object : iterable){
            Connection$Proxy connection = (Connection$Proxy) object;
            try {
                connection.shutdown();
            } catch (SQLException e) {
                logger.error("Trying to close connection from taken connections was failed: " + e.getMessage(), e);
                throw new ConnectionPoolException("Trying to take connection was interrupted: " + e.getMessage(), e);
            }
        }
    }

    private void releaseConnection(Connection connection){
        try {
            if(takenConnections.remove(connection)){
                availableConnections.put(connection);
            } else {
                logger.warn("Trying to release connection was failed");
            }
        } catch (InterruptedException e) {
            logger.warn("Trying to close connection from taken connections was failed: " + e.getMessage(), e);
        }
    }

    private void initConnections() {
        createConnections(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
        logger.info("initialization of connections was done");
    }

    private void createConnections(int valueOfConnections) {
        for (int i = 0; i < valueOfConnections; i++) {
            try {
                Connection connection = new Connection$Proxy(DriverManager.getConnection(
                        CONFIGURATION_INSTANCE.getDbUrl(),
                        CONFIGURATION_INSTANCE.getDbUser(),
                        CONFIGURATION_INSTANCE.getDbPassword()));

                availableConnections.add(connection);
            } catch (SQLException e) {
                logger.error("Trying to create connection was failed:" + e.getMessage(), e);
                throw new ConnectionPoolException("Can`t create connection to database: "+ e.getMessage(), e);
            }
        }
    }
}
