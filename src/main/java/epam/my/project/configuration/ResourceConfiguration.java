package epam.my.project.configuration;

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
    private int maxWaitTimeout;

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

    public int getMaxWaitTimeout() {
        return maxWaitTimeout;
    }

    private void initDatabaseProperties(){
        try(InputStream inputStream = Files.newInputStream(Paths.get("src/main/resources/application.properties"))){
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUrl = properties.getProperty("db.url");
            dbUser = properties.getProperty("db.user");
            dbPassword = properties.getProperty("db.password");
            dbInitialPoolSize = Integer.parseInt(properties.getProperty("db.initial.pool.size"));
            maxWaitTimeout = Integer.parseInt(properties.getProperty("db.max.wait.timeout"));

            logger.info("properties was loaded");
        } catch (IOException e){
            logger.fatal("properties has not been loaded", e);
        }
    }

}
