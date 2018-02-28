package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;


@Data
@ToString(callSuper = true)
public abstract class BaseAccountErrorEvent extends AbstractDomainEvent {

    // extends AbstractDomainEvent: message, exceptionClassName

    protected String message;
    protected String exceptionClassName;
}