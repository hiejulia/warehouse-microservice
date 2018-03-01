package com.project.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class SaveShipmentException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "Shipment data save error";

    public SaveShipmentException(String msg) {
        super( msg);
    }

    public SaveShipmentException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }
}