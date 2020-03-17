package epam.my.project.dao;

import epam.my.project.entity.Account;

public interface AccountDAO {
    Account getAccountById(int id);

    Account getAccountByEmailAndPassword(String email, String password);

    Account createAccount(Account account);

    boolean deleteAccount(int id);

    void updateAccount(int id, Account account);

}
