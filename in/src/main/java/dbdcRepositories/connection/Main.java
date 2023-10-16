package dbdcRepositories.connection;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {

        Connection connection  = DatabaseManager.getConnection();

        try {
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            database.setDefaultSchemaName("migration");
            Liquibase liquibase = new Liquibase("db/changelog/changelog.xml", new ClassLoaderResourceAccessor(), database);
            liquibase.setChangeLogParameter("liquibase.schema", "migration");
            liquibase.update(new Contexts(), new LabelExpression());

            System.out.println("Миграции успешно выполнены!");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}

