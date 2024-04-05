package db.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static final String RESOURCE_FILE_PATH = "src/main/resources/application.yml";

    private static Connection connection;

    private static final Properties properties = setProperties();

    private DatabaseConnection() {
    }

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = properties.getProperty("url");
                String name = properties.getProperty("name");
                String user = properties.getProperty("username");
                String password = properties.getProperty("password");
                String option = properties.getProperty("option");
                Class.forName(properties.getProperty("driver-name"));

                connection = DriverManager.getConnection("jdbc:mysql://" + url + "/" + name + option, user,
                        password);
                connection.setAutoCommit(false);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("찾으려는 클래스가 존재하지 않습니다. : " + e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 연결에 실패했습니다. : " + e.getMessage());
        }
        return connection;
    }

    public static void tryCloseConnection() {
        if (connection != null) {
            closeConnection();
        }
    }

    private static void closeConnection() {
        try {
            closeIfNot();
        } catch (SQLException e) {
            throw new RuntimeException("데이터베이스 컨넥션을 닫는 것에 실패했습니다. : " + e.getMessage());
        }
    }

    private static void closeIfNot() throws SQLException {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    private static Properties setProperties() {
        Properties properties = new Properties();

        try (FileInputStream inputStream = new FileInputStream(RESOURCE_FILE_PATH)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}
