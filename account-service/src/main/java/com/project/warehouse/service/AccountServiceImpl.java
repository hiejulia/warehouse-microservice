package com.project.warehouse.service;


import com.project.warehouse.event.model.AccountNotFoundEvent;
import com.project.warehouse.event.model.RemoveAccountErrorEvent;
import com.project.warehouse.event.model.SaveAccountErrorEvent;
import com.project.warehouse.event.service.EventDomainPubblishService;
import com.project.warehouse.exception.ConflictSaveAccountException;
import com.project.warehouse.exception.RemoveAccountException;
import com.project.warehouse.exception.SaveAccountException;
import com.project.warehouse.model.Account;
import com.project.warehouse.repository.AccountRepository;
import com.project.warehouse.validator.AccountDataValidationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@Data
@Service // spring service
@Transactional
public class AccountServiceImpl implements AccountService {
    // autowired : AccountDataValidationService, EventDomainPubblishService, AccountRepository
    @Autowired
    private AccountDataValidationService accountDataValidationService;

    @Autowired
    private EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    private AccountRepository accountRepository;

    // POST create new account
    @Override
    public Account createAccount(Account account) {
        // UUID
        String correlationId = UUID.randomUUID().toString();
        // validation account before saving
        accountDataValidationService.validate(correlationId, account);
        // save account and check duplication
        Account save = doSaveAccountData(correlationId, account, true);
        // fire save account event
        eventDomainPubblishService.publishAccountCreationEvent(correlationId, save.getUserName());
        return save;
    }

    @Override
    @Transactional(readOnly = true) // @Transactional : readOnly = true
    public List<Account> findAccountList() {
        return accountRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Account findAccount(String userName) {
        doCheckAccountExist(UUID.randomUUID().toString(),userName);
        return accountRepository.findOne(userName);
    }

    @Override
    public Account updateAccount(Account account) {
        String correlationId = UUID.randomUUID().toString();

        // data validation
        accountDataValidationService.validate(correlationId, account);

        // check the presence of an account in the database
        doCheckAccountExist(correlationId, account.getUserName());

        // save account
        Account save = doSaveAccountData(correlationId, account, false);

        if(!account.getPassword().equals(save.getPassword())){
            // fire change password account event
            eventDomainPubblishService.publishChangeAccountPasswordEvent(correlationId, save.getUserName());
        }
        // fire save account event
        return save;
    }

    @Override
    public void deleteAccount(String userName) {
        String correlationId = UUID.randomUUID().toString();
        // data validation
        accountDataValidationService.validateUserName(correlationId,userName);
        // delete account
        doDeleteAccount(correlationId, userName);
        // fire remove account event
        eventDomainPubblishService.publishRemoveAccountEvent(correlationId,userName);
    }

    private Account doSaveAccountData(String correlationId, Account account, boolean checkDuplicate) {
        Account accountAux = account;
        ConflictSaveAccountException conflictSaveAccountException = null;
        SaveAccountErrorEvent saveAccountErrorEvent;
        try{
            if(checkDuplicate){
                Account one = accountRepository.findOne(account.getUserName());
                if(one == null){
                    accountAux = accountRepository.saveAndFlush(account);
                } else {
                    saveAccountErrorEvent = eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, account.getUserName(),
                            ConflictSaveAccountException.DEFAULT_MESSAGE, ConflictSaveAccountException.class);
                    conflictSaveAccountException = new ConflictSaveAccountException(saveAccountErrorEvent, ConflictSaveAccountException.DEFAULT_MESSAGE);
                }
            }else {
                accountAux = accountRepository.saveAndFlush(account);
            }
        } catch (Exception e){
            saveAccountErrorEvent = eventDomainPubblishService.publishSaveAccountErrorEvent(correlationId, account.getUserName(),
                    SaveAccountException.DEFAULT_MESSAGE, SaveAccountException.class);
            throw new SaveAccountException(saveAccountErrorEvent, SaveAccountException.DEFAULT_MESSAGE);
        }

        if(conflictSaveAccountException!= null){
            throw conflictSaveAccountException;
        }

        return accountAux;
    }

    private void doCheckAccountExist(String correlationId, String userName) {
        Account accountAux;

        Function<String, AccountNotFoundException> f = userNameAux -> {
            AccountNotFoundEvent accountNotFoundEvent = eventDomainPubblishService.publishAccountNotFoundEvent(correlationId, userNameAux);
//            return new AccountNotFoundException(accountNotFoundEvent, AccountNotFoundException.DEFAULT_MESSAGE);
            return null;
        };

        try{
            accountAux =  accountRepository.findOne(userName);
            if(accountAux == null){
                throw f.apply(userName);
            }
        } catch (Exception e){
//            throw f.apply(userName);
        }
    }

    private void doDeleteAccount(String correlationId, String userName) {
        doCheckAccountExist(correlationId, userName);
        try{
            accountRepository.delete(userName);
        } catch (Exception e){
            RemoveAccountErrorEvent removeAccountErrorEvent = eventDomainPubblishService.publishRemoveAccountErrorEvent(correlationId, userName,RemoveAccountException.DEFAULT_MESSAGE,RemoveAccountException.class);
            throw new RemoveAccountException(removeAccountErrorEvent, RemoveAccountException.DEFAULT_MESSAGE);
        }
    }
}