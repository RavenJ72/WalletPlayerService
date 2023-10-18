package jbdcRepositories;


import jbdcRepositories.connection.DatabaseManager;
import model.BankAccount;
import model.Transaction;
import modelRepositoriesI.BankAccountRepository;
import modelRepositoriesI.TransactionRepository;

import java.math.BigDecimal;
import java.sql.*;

public class BankAccountRepositoryImpl implements BankAccountRepository {

    private final TransactionRepository transactionRepository;
    private final String url;

    public BankAccountRepositoryImpl(TransactionRepository transactionRepository, String url) {
        this.transactionRepository = transactionRepository;
        this.url = url;
    }

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

    @Override
    public boolean withdrawMoney(Long bankAccountId, BigDecimal amount, Long transactionId) {
        Connection connection = null;
        try {
            connection = DatabaseManager.getConnection(url);
            connection.setAutoCommit(false);

            // Проверяем баланс
            String checkBalanceQuery = "SELECT balance FROM wallet.bank_account WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(checkBalanceQuery);
            preparedStatement.setLong(1, bankAccountId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                BigDecimal balance = resultSet.getBigDecimal("balance");
                if (balance.compareTo(amount) < 0) { // Недостаточно средств
                    return false;
                }

                if(transactionRepository.save(new Transaction(transactionId,"DEBIT",amount,bankAccountId)) == null){
                    return false;
                }

                // Снимаем деньги
                String withdrawQuery = "UPDATE wallet.bank_account SET balance = balance - ? WHERE id = ?";
                preparedStatement = connection.prepareStatement(withdrawQuery);
                preparedStatement.setBigDecimal(1, amount);
                preparedStatement.setLong(2, bankAccountId);
                preparedStatement.executeUpdate();



                connection.commit();
                return true;
            }

        } catch (Exception ex) {
            System.err.println("Произошла ошибка при снятии денег.");
            System.err.println(ex.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Ошибка при откате транзакции.");
                }
            }
            return false;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (Exception ex) {
                    System.err.println("Ошибка при закрытии соединения.");
                }
            }
        }
        return false;
    }

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
            System.err.println("Произошла ошибка при внесении денег.");
            System.err.println(ex.getMessage());
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.err.println("Ошибка при откате транзакции.");
                }
            }
            return false;

        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (Exception ex) {
                    System.err.println("Ошибка при закрытии соединения.");
                }
            }
        }
    }




}
