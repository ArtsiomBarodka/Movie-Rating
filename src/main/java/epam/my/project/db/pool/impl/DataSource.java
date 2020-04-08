package epam.my.project.db.pool.impl;

import static epam.my.project.configuration.ApplicationConfiguration.*;
import static org.apache.logging.log4j.LogManager.getLogger;

import epam.my.project.db.pool.ConnectionPool;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.ReentrantLock;


public enum DataSource implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private final Logger logger = getLogger(DataSource.class);

    private ReentrantLock locker = new ReentrantLock();
    private BlockingQueue<Connection> availableConnections = new LinkedBlockingQueue<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
    private List<Connection> takenConnections = new ArrayList<>(CONFIGURATION_INSTANCE.getDbInitialPoolSize());

    DataSource(){
        initConnections();
    }


    public Connection getConnection() {
        Connection connection = null;
            try {
                connection = availableConnections.take();
                locker.lock();
                takenConnections.add(connection);
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
            } finally {
                locker.unlock();
            }
        return connection;
    }

    public void releaseConnection(Connection connection) {
            try {
                if(takenConnections.remove(connection)){
                    locker.lock();
                    availableConnections.put(connection);
                }
            } catch (InterruptedException e) {
                logger.error("Trying to take connection was interrupted", e);
            } finally {
                locker.unlock();
            }
    }

    public void shutdown() {
        locker.lock();
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
        locker.unlock();
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
