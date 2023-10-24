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

/**
 * Provides functionality for managing database connections and executing migrations.
 *
 * This class encapsulates logic for establishing connections to the database, executing
 * schema migrations, and retrieving database configurations.
 *
 * @author Gleb Nickolaenko
 */
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

        makeMigrations(url);
    }

    /**
     * Establishes a connection to the database.
     *
     * @param url The URL of the database.
     * @return A Connection object representing the database connection.
     */
    public static Connection getConnection(String url){
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Executes schema migrations to set up the necessary database structures.
     *
     * @param url The URL of the database to execute migrations against.
     */
    public static void makeMigrations(String url){
        // Establishing a connection to the database
        Connection connection = getConnection(url);
        Statement statement = null;
        try {
            statement = connection.createStatement();
            // Creating the migration schema for Liquibase system tables
            statement.execute("CREATE SCHEMA IF NOT EXISTS migration");

            // Creating the wallet schema for application tables
            statement.execute("CREATE SCHEMA IF NOT EXISTS wallet");

            try {
                Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
                database.setDefaultSchemaName("migration");
                Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
                liquibase.setChangeLogParameter("liquibase.schema", "migration");
                liquibase.update(new Contexts(), new LabelExpression());
                connection.close();
                System.out.println("Migrations successfully executed!");

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Retrieves the database URL.
     *
     * @return The URL of the database.
     */
    public static String getUrl(){
        return url;
    }
}
