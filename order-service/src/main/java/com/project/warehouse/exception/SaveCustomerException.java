package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveCustomerException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "customer data save error";

    public SaveCustomerException(String msg) {
        super( msg);
    }

    public SaveCustomerException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}