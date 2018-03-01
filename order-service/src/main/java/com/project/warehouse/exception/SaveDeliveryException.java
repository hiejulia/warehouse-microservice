package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveDeliveryException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "delivery data save error";

    public SaveDeliveryException(String msg) {
        super( msg);
    }

    public SaveDeliveryException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}