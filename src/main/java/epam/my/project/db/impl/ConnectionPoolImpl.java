package epam.my.project.db.impl;

import epam.my.project.db.ConnectionPool;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public enum  ConnectionPoolImpl implements ConnectionPool {
    CONNECTION_POOL_INSTANCE;

    private List<Connection> availableConnections = new ArrayList<>();
    private List<Connection> takenConnections = new ArrayList<>();

    ConnectionPoolImpl(){

    }


    public Connection getConnection() {
        return null;
    }

    public boolean releaseConnection(Connection connection) {
        return false;
    }

    public void shutdown() {

    }
}
