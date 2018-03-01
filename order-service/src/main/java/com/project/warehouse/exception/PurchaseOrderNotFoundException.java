package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PurchaseOrderNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "purchase order didn't found";

    public PurchaseOrderNotFoundException(String msg) {
        super(msg);
    }

    public PurchaseOrderNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}