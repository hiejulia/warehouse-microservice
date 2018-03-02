package com.project.warehouse.exception;


import com.project.warehouse.event.model.AbstractDomainEvent;
import lombok.Getter;

// AbstractException class

@Getter
public abstract class AbstractException extends RuntimeException {


    protected final AbstractDomainEvent event;

    public AbstractException(AbstractDomainEvent event, String msg) {
        super(msg);
        this.event = event;
    }

    public AbstractException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(msg, cause);
        this.event = event;
    }
}