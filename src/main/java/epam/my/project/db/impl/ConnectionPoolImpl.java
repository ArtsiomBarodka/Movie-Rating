package epam.my.project.db.impl;

import static epam.my.project.config.ApplicationConfiguration.*;
import static org.apache.logging.log4j.LogManager.getLogger;

import epam.my.project.db.ConnectionPool;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;


public enum  ConnectionPoolImpl implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private static final Logger logger = getLogger(ConnectionPoolImpl.class);

    private boolean isBlocked = false;
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private BlockingDeque<Connection> takenConnections = new LinkedBlockingDeque<>();

    ConnectionPoolImpl(){
        initConnections();
    }


    public Connection getConnection() {
        Connection connection = null;

        if(!isBlocked){
            try {
                connection = availableConnections.take();
                takenConnections.putLast(connection);
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
            }
        }

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        if(!isBlocked){
            try {
                if(takenConnections.removeIf((c)-> c == connection)){
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
                con.getConnection().close();
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
