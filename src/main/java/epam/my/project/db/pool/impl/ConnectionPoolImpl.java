package epam.my.project.db.pool.impl;

import static epam.my.project.config.ApplicationConfiguration.*;
import static org.apache.logging.log4j.LogManager.getLogger;

import epam.my.project.db.pool.ConnectionPool;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public enum  ConnectionPoolImpl implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private final Logger logger = getLogger(ConnectionPoolImpl.class);

    private boolean isBlocked = false;
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private BlockingQueue<Connection> takenConnections = new LinkedBlockingQueue<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());

    ConnectionPoolImpl(){
        initConnections();
    }


    public Connection getConnection() {
        Connection connection = null;

        if(!isBlocked){
            try {
                connection = availableConnections.take();
                takenConnections.put(connection);
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
            }
        }

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if(!isBlocked){
            try {
                if(takenConnections.remove(connection)){
                    availableConnections.put(connection);
                    return true;
                }
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
            }
        }
        return false;
    }

    public void shutdown() {
        isBlocked = true;
        takenConnections.forEach(this::releaseConnection);

        for (Connection connection : availableConnections){
            Connection$Proxy con = (Connection$Proxy) connection;
            try {
                con.shutdown();
            } catch (SQLException e) {
                logger.error("Trying to close connection from taken connections was failed", e);
            }
        }

        availableConnections.clear();

        logger.info("shutdown of connections was done");
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
                logger.fatal("Trying to create connection was failed", e);
            }
        }
    }
}
