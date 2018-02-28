package com.project.warehouse.exception;


import com.project.warehouse.event.model.SaveAccountErrorEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveAccountException extends AbstractAccountException {

    public static final String DEFAULT_MESSAGE  = "Error happened when saving account ";

    public SaveAccountException(SaveAccountErrorEvent event, String msg) {
        super(event, msg);
    }

    public SaveAccountException(SaveAccountErrorEvent event,String msg, Throwable cause) {
        super(event, msg, cause);
    }
}