package epam.my.project.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public enum  ApplicationConfiguration {
    CONFIGURATION_INSTANCE;

    private String dbUrl;
    private String dbUser;
    private String dbPassword;
    private int dbInitialPoolSize;

    ApplicationConfiguration() {
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

    private void initDatabaseProperties(){
        try(InputStream inputStream = Files.newInputStream(Paths.get("./src/main/resources/database.properties"))){
            Properties properties = new Properties();
            properties.load(inputStream);
            dbUrl = properties.getProperty("url");
            dbUser = properties.getProperty("user");
            dbPassword = properties.getProperty("password");
            dbInitialPoolSize = Integer.parseInt(properties.getProperty("initial.pool.size"));

        } catch (IOException e){
            System.out.println("File not found: " + e);
            throw new Error("Properties has not been loaded", e);
        }
    }

}
