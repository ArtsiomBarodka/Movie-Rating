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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public enum  ConnectionPoolImpl implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private static final Logger logger = getLogger(ConnectionPoolImpl.class);


    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<Connection>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private List<Connection> takenConnections = new ArrayList<Connection>();

    ConnectionPoolImpl(){
        initConnections();
    }


    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = availableConnections.take();
            takenConnections.add(connection);
        } catch (InterruptedException e) {
           logger.error("Trying to take connection was interrupted", e);
        }

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        availableConnections.add(connection);
        return takenConnections.remove(connection);
    }

    public void shutdown() {
        for (Connection connection : takenConnections){
            Connection$Proxy con = (Connection$Proxy) connection;
            try {
                con.getConnection().close();
            } catch (SQLException e) {
                logger.error("Trying to close connection from taken connections was failed", e);
            }
        }

        for (Connection connection : availableConnections){
            Connection$Proxy con = (Connection$Proxy) connection;
            try {
                con.getConnection().close();
            } catch (SQLException e) {
                logger.error("Trying to close connection from available connections was failed", e);
            }
        }

        availableConnections.clear();
        takenConnections.clear();

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
