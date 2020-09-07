package com.epam.mrating.dao.factory;

import com.epam.mrating.dao.impl.jdbc.DAOJDBCFactory;

/**
 * The type Dao configuration.
 */
public final class DAOManager {
    private static DAOFactory jdbcDaoFactory;
    static {
        init();
    }

    private DAOManager() {
    }

    private static void init(){
        jdbcDaoFactory = DAOJDBCFactory.DAO_JDBC_FACTORY;
    }

    /**
     * Get factory dao factory.
     *
     * @param daoType the dao type
     * @return the dao factory
     */
    public static DAOFactory getFactory(DAOType daoType){
        switch (daoType){
            case JDBC:
                return jdbcDaoFactory;

            default:
                return null;
        }
    }
}
