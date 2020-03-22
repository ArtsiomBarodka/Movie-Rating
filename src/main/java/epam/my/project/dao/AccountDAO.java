package epam.my.project.dao;

import epam.my.project.entity.Account;

import java.sql.SQLException;

public interface AccountDAO {
    Account getAccountById(int id) throws SQLException;

    Account getAccountByEmailAndPassword(String email, String password) throws SQLException;

    Account createAccount(Account account) throws SQLException;

    boolean deleteAccount(int id) throws SQLException;

    void updateAccount(int id, Account account) throws SQLException;

}
