package jbdcRepositories.connection;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseManager {
    private static final String DB_PROPERTIES_FILE = "/database.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        Properties properties = new Properties();
        try (InputStream input = DatabaseManager.class.getResourceAsStream(DB_PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        url = properties.getProperty("db.url");
        username = properties.getProperty("db.username");
        password = properties.getProperty("db.password");
    }



    public static Connection getConnection(String url){
        try {
            return DriverManager.getConnection(url,username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeMigrations(String url){
        // Создание соединения с базой данных
        Connection connection = getConnection(url);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            // Создание схемы migration для системных таблиц Liquibase
            statement.execute("CREATE SCHEMA IF NOT EXISTS migration");

            // Создание схемы wallet для таблиц приложения
            statement.execute("CREATE SCHEMA IF NOT EXISTS wallet");

            try {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                database.setDefaultSchemaName("migration");
                Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.setChangeLogParameter("liquibase.schema", "migration");
                liquibase.update(new Contexts(), new LabelExpression());
                connection.close();
                System.out.println("Миграции успешно выполнены!");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    public static String getUrl(){
        return url;
    }




}
