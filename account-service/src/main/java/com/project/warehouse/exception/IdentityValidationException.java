package com.project.warehouse.exception;


import com.project.warehouse.event.model.IdentityValidationErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class IdentityValidationException extends AbstractAccountException {
    public IdentityValidationException(IdentityValidationErrorEvent event, String msg) {
        super(event, msg);
    }

    public IdentityValidationException(IdentityValidationErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}