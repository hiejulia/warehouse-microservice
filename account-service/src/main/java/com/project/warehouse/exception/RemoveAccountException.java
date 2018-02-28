package com.project.warehouse.exception;

import com.project.warehouse.event.model.RemoveAccountErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class RemoveAccountException extends AbstractAccountException {

    public static final String DEFAULT_MESSAGE = "Error happened when delete account";

    public RemoveAccountException(RemoveAccountErrorEvent event, String msg) {
        super(event, msg);
    }

    public RemoveAccountException(RemoveAccountErrorEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}