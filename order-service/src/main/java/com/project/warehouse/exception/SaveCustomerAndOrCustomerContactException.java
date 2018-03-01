package com.project.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveCustomerAndOrCustomerContactException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "customer and customer contact data save error";

    public SaveCustomerAndOrCustomerContactException(String msg) {
        super( msg);
    }

    public SaveCustomerAndOrCustomerContactException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}