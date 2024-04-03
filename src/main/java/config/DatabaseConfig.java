package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DatabaseConfig {
    private String server;
    private String database;
    private String option;
    private String username;
    private String password;

    public DatabaseConfig() {
        final Properties properties = new Properties();
        try (final FileInputStream inputStream = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(inputStream);
            this.server = properties.getProperty("SERVER");
            this.database = properties.getProperty("DATABASE");
            this.option = properties.getProperty("OPTION");
            this.username = properties.getProperty("USERNAME");
            this.password = properties.getProperty("PASSWORD");
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    public String getServer() {
        return server;
    }

    public String getDatabase() {
        return database;
    }

    public String getOption() {
        return option;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
