package com.project.warehouse.exception;


import com.project.warehouse.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PriceListNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "Price list didn't found";

    public PriceListNotFoundException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public PriceListNotFoundException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}

