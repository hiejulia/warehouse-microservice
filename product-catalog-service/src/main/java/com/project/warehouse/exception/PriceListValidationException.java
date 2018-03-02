package com.project.warehouse.exception;



import com.project.warehouse.event.model.AbstractDomainEvent;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PriceListValidationException extends AbstractException {
    public PriceListValidationException(AbstractDomainEvent event, String msg) {
        super(event, msg);
    }

    public PriceListValidationException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(event, msg, cause);
    }
}