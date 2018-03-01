package com.project.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "Account not found";

    public AccountNotFoundException(String msg) {
        super( msg);
    }

    public AccountNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}