package com.project.warehouse.exception;



import com.project.warehouse.event.model.AccountNotFoundEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends AbstractAccountException {

    public static final String DEFAULT_MESSAGE  = "Account not found";

    public AccountNotFoundException(AccountNotFoundEvent event, String msg) {
        super(event, msg);
    }

    public AccountNotFoundException(AccountNotFoundEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}