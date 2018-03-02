package com.project.warehouse.exception;


import com.project.warehouse.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductsValidationException extends AbstractException {
    public ProductsValidationException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public ProductsValidationException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}