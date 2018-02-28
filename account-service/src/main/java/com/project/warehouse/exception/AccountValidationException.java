package com.project.warehouse.exception;


import com.project.warehouse.event.model.AccountValidationErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountValidationException extends AbstractAccountException {
    public AccountValidationException(AccountValidationErrorEvent event, String msg) {
        super(event, msg);
    }

    public AccountValidationException(AccountValidationErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}