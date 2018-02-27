package com.project.warehouse.exception;


import lombok.Getter;

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