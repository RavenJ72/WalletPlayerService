package dbdcRepositories.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private static final String DB_PROPERTIES_FILE = "/database.properties";
    private static Connection connection;

    static {
        try {
            // Загрузка настроек из файла конфигурации при инициализации класса
            Properties properties = new Properties();
            try (InputStream input = DatabaseManager.class.getResourceAsStream(DB_PROPERTIES_FILE)) {
                properties.load(input);
            }

            // Получение параметров подключения
            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            // Создание соединения с базой данных
            connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Создание схемы migration для системных таблиц Liquibase
            statement.execute("CREATE SCHEMA IF NOT EXISTS migration");

            // Создание схемы wallet для таблиц приложения
            statement.execute("CREATE SCHEMA IF NOT EXISTS wallet");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    // Можете добавить другие методы для управления базой данных
}
