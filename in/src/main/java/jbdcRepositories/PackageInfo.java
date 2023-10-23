/**
 * This package contains the JDBC repository implementations for the application.
 *
 * The repository implementations in this package provide concrete JDBC-based implementations
 * of the repository interfaces defined in the modelRepositoriesI package.
 * These implementations interact with a relational database to persist and retrieve
 * application data, encapsulating the JDBC and SQL code necessary to manage the
 * database connections and execute SQL queries.
 *
 * The classes in this package use the DatabaseManager class
 *
 * Each repository implementation class corresponds to a specific entity in the application's
 * domain model and provides CRUD (Create, Read, Update, Delete) operations for that entity.
 * The repository implementations also manage the relationships between entities by handling
 * foreign key constraints and executing the appropriate SQL queries to retrieve related entities.
 *
 * The repository implementations in this package are designed to be used with a PostgreSQL database,
 * as indicated by the SQL syntax and the database connection configuration in the DatabaseManager class.
 *
 * @author Gleb Nickolaenko
 */
package jbdcRepositories;
