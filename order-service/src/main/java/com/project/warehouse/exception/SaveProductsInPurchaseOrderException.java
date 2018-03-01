package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveProductsInPurchaseOrderException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "purchase order data save error";

    public SaveProductsInPurchaseOrderException(String msg) {
        super( msg);
    }

    public SaveProductsInPurchaseOrderException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}