package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.CONFLICT)
public class PurchaseOrderInvalidOperationOnStatusException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "operation denied on purchase order";

    public PurchaseOrderInvalidOperationOnStatusException(String msg) {
        super(msg);
    }

    public PurchaseOrderInvalidOperationOnStatusException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}