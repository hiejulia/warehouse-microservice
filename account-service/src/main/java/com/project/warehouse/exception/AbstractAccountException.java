package com.project.warehouse.exception;


import com.project.warehouse.event.model.AbstractDomainEvent;
import lombok.Getter;

@Getter
public abstract class AbstractAccountException extends RuntimeException {

    // abstract class for exception

    protected final AbstractDomainEvent event;

    public AbstractAccountException(AbstractDomainEvent event, String msg) {
        super(msg);
        this.event = event;
    }

    public AbstractAccountException(AbstractDomainEvent event, String msg, Throwable cause) {
        super(msg, cause);
        this.event = event;
    }
}