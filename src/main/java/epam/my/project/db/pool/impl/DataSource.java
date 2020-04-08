package epam.my.project.db.pool.impl;

import static epam.my.project.configuration.ApplicationConfiguration.*;
import static org.apache.logging.log4j.LogManager.getLogger;

import epam.my.project.db.pool.ConnectionPool;
import epam.my.project.exception.InternalServerErrorException;
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

    DataSource(){
        initConnections();
    }

    public Connection getConnection() {
        Connection connection;
            try {
                connection = availableConnections.poll(5, TimeUnit.SECONDS);
                locker.lock();
                if(Objects.isNull(connection)){
                    logger.warn("Can`t get connection to database. Connection is null");
                    throw new InternalServerErrorException("Can`t get connection to database");
                }
                takenConnections.add(connection);
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
                throw new InternalServerErrorException("Can`t get connection to database");
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
                logger.warn("Trying to close connection from taken connections was failed", e);
            }
        }
    }

    private void releaseConnection(Connection connection){
        try {
            if(takenConnections.remove(connection)){
                availableConnections.put(connection);
            }
        } catch (InterruptedException e) {
            logger.warn("Trying to take connection was interrupted", e);
        }
    }

    private void initConnections(){
        createConnections(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
        logger.info("initialization of connections was done");
    }

    private void createConnections(int valueOfConnections){
        for (int i = 0; i < valueOfConnections; i++) {
            try {
                Connection connection = new Connection$Proxy(DriverManager.getConnection(
                        CONFIGURATION_INSTANCE.getDbUrl(),
                        CONFIGURATION_INSTANCE.getDbUser(),
                        CONFIGURATION_INSTANCE.getDbPassword()));

                availableConnections.add(connection);
            } catch (SQLException e) {
                logger.error("Trying to create connection was failed", e);
                throw new InternalServerErrorException("Can`t create connection to database");
            }
        }
    }
}
