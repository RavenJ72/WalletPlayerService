package jbdcRepositories;

import jbdcRepositories.connection.DatabaseManager;
import model.BankAccount;
import model.Transaction;
import modelRepositoriesI.BankAccountRepository;
import modelRepositoriesI.TransactionRepository;

import java.math.BigDecimal;
import java.sql.*;

/**
 * Implementation of the BankAccountRepository interface for managing bank accounts.
 * This class provides methods to interact with the database for saving, finding,
 * depositing, and withdrawing money in/from bank accounts.
 *
 * @author Gleb Nickolaenko
 */
public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final TransactionRepository transactionRepository;
    private final String url;

    /**
     * Constructs a new BankAccountRepositoryImpl with the specified transaction repository and database URL.
     *
     * @param transactionRepository The transaction repository for managing transactions.
     * @param url The URL of the database.
     */
    public BankAccountRepositoryImpl(TransactionRepository transactionRepository, String url) {
        this.transactionRepository = transactionRepository;
        this.url = url;
    }

    /**
     * Saves a new bank account with a zero balance to the database.
     *
     * @param bankAccount The bank account object to be saved.
     * @return The saved bank account object with updated ID.
     */
    @Override
    public BankAccount save(BankAccount bankAccount) {
        String sql = "INSERT INTO wallet.bank_account (balance) VALUES (0) RETURNING id";

        try (Connection connection = DatabaseManager.getConnection(url);
             Statement statement = connection.createStatement()) {

            connection.setAutoCommit(false);

            try (ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    bankAccount.setId(rs.getLong("id"));
                }
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return bankAccount;
    }

    /**
     * Finds and retrieves a bank account from the database by its unique ID.
     *
     * @param id The unique ID of the bank account to find.
     * @return The found bank account object, or null if not found.
     */
    @Override
    public BankAccount findById(Long id) {
        String sql = "SELECT id, balance FROM wallet.bank_account WHERE id = " + id;
        try {
            Connection connection = DatabaseManager.getConnection(url);
            Statement statement = connection.createStatement();
            try (ResultSet rs = statement.executeQuery(sql)) {
                if (rs.next()) {
                    BankAccount bankAccount = new BankAccount();
                    bankAccount.setId(rs.getLong("id"));
                    bankAccount.setBalance(rs.getBigDecimal("balance"));
                    return bankAccount;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }

    /**
     * Withdraws a specified amount of money from a bank account.
     * This method checks the balance, creates a debit transaction,
     * and updates the bank account balance in the database.
     *
     * @param bankAccountId The ID of the bank account to withdraw money from.
     * @param amount The amount of money to withdraw.
     * @param transactionId The ID of the transaction.
     * @return true if the withdrawal was successful, false otherwise.
     */
    @Override
    public boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection(url);
            connection.setAutoCommit(false);

            // Check balance
            String checkBalanceQuery = "SELECT balance FROM wallet.bank_account WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkBalanceQuery);
            preparedStatement.setLong(1, bankAccountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                BigDecimal balance = resultSet.getBigDecimal("balance");
                if (balance.compareTo(amount) < 0) { // Insufficient funds
                    return false;
                }

                if(transactionRepository.save(new Transaction(transactionId,"DEBIT",amount,bankAccountId)) == null){
                    return false;
                }

                // Withdraw money
                String withdrawQuery = "UPDATE wallet.bank_account SET balance = balance - ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(withdrawQuery);
                preparedStatement.setBigDecimal(1, amount);
                preparedStatement.setLong(2, bankAccountId);
                preparedStatement.executeUpdate();

                connection.commit();
                return true;
            }

        } catch (Exception ex) {
            System.err.println("An error occurred while withdrawing money.");
            System.err.println(ex.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during transaction rollback.");
                }
            }
            return false;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (Exception ex) {
                    System.err.println("Error closing the connection.");
                }
            }
        }
        return false;
    }

    /**
     * Deposits a specified amount of money to a bank account.
     * This method creates a credit transaction and updates the bank
     * account balance in the database.
     *
     * @param bankAccountId The ID of the bank account to deposit money to.
     * @param amount The amount of money to deposit.
     * @param transactionId The ID of the transaction.
     * @return true if the deposit was successful, false otherwise.
     */
    @Override
    public boolean depositMoney(Long bankAccountId, BigDecimal amount, Long transactionId) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection(url);
            connection.setAutoCommit(false);

            if(transactionRepository.save(new Transaction(transactionId,"CREDIT",amount,bankAccountId)) == null){
                return false;
            }

            String depositQuery = "UPDATE wallet.bank_account SET balance = balance + ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(depositQuery);
            preparedStatement.setBigDecimal(1, amount);
            preparedStatement.setLong(2, bankAccountId);
            preparedStatement.executeUpdate();

            connection.commit();
            return true;

        } catch (Exception ex) {
            System.err.println("An error occurred while depositing money.");
            System.err.println(ex.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Error during transaction rollback.");
                }
            }
            return false;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (Exception ex) {
                    System.err.println("Error closing the connection.");
                }
            }
        }
    }
}
