package epam.my.project.configuration;

import epam.my.project.exception.ConfigException;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.apache.logging.log4j.LogManager.getLogger;

/**
 * The enum Resource configuration.
 */
public enum ResourceConfiguration {
    /**
     * Configuration instance resource configuration.
     */
    CONFIGURATION_INSTANCE;

    private final Logger logger = getLogger(ResourceConfiguration.class);

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int dbInitialPoolSize;
    private int dbMaxWaitTimeout;
    private String googleAppId;
    private String host;
    private String facebookAppId;
    private String facebookSecret;

    ResourceConfiguration() {
        initDatabaseProperties();
    }

    /**
     * Gets db url.
     *
     * @return the db url
     */
    public String getDbUrl() {
        return dbUrl;
    }

    /**
     * Gets db user.
     *
     * @return the db user
     */
    public String getDbUser() {
        return dbUser;
    }

    /**
     * Gets db password.
     *
     * @return the db password
     */
    public String getDbPassword() {
        return dbPassword;
    }

    /**
     * Gets db initial pool size.
     *
     * @return the db initial pool size
     */
    public int getDbInitialPoolSize() {
        return dbInitialPoolSize;
    }

    /**
     * Gets db max wait timeout.
     *
     * @return the db max wait timeout
     */
    public int getDbMaxWaitTimeout() {
        return dbMaxWaitTimeout;
    }

    /**
     * Gets google app id.
     *
     * @return the google app id
     */
    public String getGoogleAppId() {
        return googleAppId;
    }

    /**
     * Gets facebook app id.
     *
     * @return the facebook app id
     */
    public String getFacebookAppId() {
        return facebookAppId;
    }

    /**
     * Gets facebook secret.
     *
     * @return the facebook secret
     */
    public String getFacebookSecret() {
        return facebookSecret;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    private void initDatabaseProperties(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        try(InputStream inputStream = loader.getResourceAsStream("application.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            dbInitialPoolSize = Integer.parseInt(properties.getProperty("db.initial.pool.size"));
            dbMaxWaitTimeout = Integer.parseInt(properties.getProperty("db.max.wait.timeout"));
            host = properties.getProperty("app.host");
            googleAppId = properties.getProperty("social.google.app-id");
            facebookAppId = properties.getProperty("social.facebook.app-id");
            facebookSecret = properties.getProperty("social.facebook.secret");
            logger.info("Properties was loaded");
        } catch (IOException e){
            logger.error("Properties has not been loaded : " + e.getMessage(), e);
            throw new ConfigException("Properties has not been loaded : " + e.getMessage(), e);
        }
    }

}
