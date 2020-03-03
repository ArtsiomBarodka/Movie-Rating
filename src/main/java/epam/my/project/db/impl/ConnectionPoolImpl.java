package epam.my.project.db.impl;

import static epam.my.project.config.ApplicationConfiguration.*;

import epam.my.project.config.ApplicationConfiguration;
import epam.my.project.db.ConnectionPool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

public enum  ConnectionPoolImpl implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private List<Connection> availableConnections = new CopyOnWriteArrayList<Connection>(new ArrayList<Connection>(CONFIGURATION_INSTANCE.getDbInitialPoolSize()));
    private List<Connection> takenConnections = new ArrayList<>();

    ConnectionPoolImpl(){
        initConnections();
    }


    public Connection getConnection() {
        Connection connection = null;

        if(!availableConnections.isEmpty()){
            connection = availableConnections.get(0);

            if(Objects.nonNull(connection)){
                takenConnections.add(connection);
                availableConnections.remove(0);
            }

        }

        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        return false;
    }

    public void shutdown() {

    }

    private void initConnections(){
        createConnections(CONFIGURATION_INSTANCE.getDbInitialPoolSize());
        //log
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
                //log
            }
        }
    }
}
