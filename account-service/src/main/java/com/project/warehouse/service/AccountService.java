package com.project.warehouse.service;




import com.project.warehouse.model.Account;

import java.util.List;

// interface service
public interface AccountService {

    // POST create new account : Account
    Account createAccount(Account account);

    // GET get all accounts
    List<Account> findAccountList();

    // GET find account : by username
    Account findAccount(String userName);

    // PUT update account : Account
    Account updateAccount(Account account);

    // DELETE delete account: by id, by username

    void deleteAccount(String userName);
}