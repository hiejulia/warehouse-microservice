package com.project.warehouse.validator;


import com.project.warehouse.exception.AccountValidationException;
import com.project.warehouse.model.Account;

public interface AccountDataValidationService {

    void validate(String correlationId, Account account) throws AccountValidationException;

    void validateUserName(String correlationId, String userName) throws AccountValidationException;
}