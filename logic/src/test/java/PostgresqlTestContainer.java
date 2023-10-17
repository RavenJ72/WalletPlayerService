import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class PostgresqlTestContainer {
    public static final String IMAGE_VERSION = "postgres:latest";
    public static final String DATABASE_NAME = "testDb";
    public static final String username = "test";
    public static final String password = "test";

    public static String url = null;
    public static PostgreSQLContainer container;

    static {

        container = new PostgreSQLContainer<>(IMAGE_VERSION)
                .withDatabaseName(DATABASE_NAME)
                .withUsername("test")
                .withPassword("test");

        url = container.getJdbcUrl();




    }


    public static PostgreSQLContainer getContainer() {
        return container;
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeMigrations(Connection connection){
        // Создание соединения с базой данных

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

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }


}

