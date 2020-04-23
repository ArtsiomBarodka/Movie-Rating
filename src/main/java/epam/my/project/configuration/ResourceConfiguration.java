package epam.my.project.configuration;

import epam.my.project.exception.ConfigException;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static org.apache.logging.log4j.LogManager.getLogger;

public enum ResourceConfiguration {
    CONFIGURATION_INSTANCE;

    private final Logger logger = getLogger(ResourceConfiguration.class);

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int dbInitialPoolSize;
    private int dbMaxWaitTimeout;
    private String googleAppId;
    private String facebookAppId;
    private String facebookSecret;

    ResourceConfiguration() {
        initDatabaseProperties();
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public int getDbInitialPoolSize() {
        return dbInitialPoolSize;
    }

    public int getDbMaxWaitTimeout() {
        return dbMaxWaitTimeout;
    }

    public String getGoogleAppId() {
        return googleAppId;
    }

    public String getFacebookAppId() {
        return facebookAppId;
    }

    public String getFacebookSecret() {
        return facebookSecret;
    }

    private void initDatabaseProperties(){
        try(InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/application.properties"))){
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            dbInitialPoolSize = Integer.parseInt(properties.getProperty("db.initial.pool.size"));
            dbMaxWaitTimeout = Integer.parseInt(properties.getProperty("db.max.wait.timeout"));
            googleAppId = System.getenv(properties.getProperty("social.google.app-id"));
            facebookAppId = properties.getProperty("social.facebook.app-id");
            facebookSecret = System.getenv(properties.getProperty("social.facebook.secret"));
            logger.info("Properties was loaded");
        } catch (IOException e){
            logger.error("Properties has not been loaded : " + e.getMessage(), e);
            throw new ConfigException("Properties has not been loaded : " + e.getMessage(), e);
        }
    }

}
