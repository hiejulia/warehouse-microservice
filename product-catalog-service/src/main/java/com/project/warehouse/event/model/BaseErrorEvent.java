package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public abstract class BaseErrorEvent extends AbstractDomainEvent {


    //error : message exceptionClassName
    protected String message;

    protected String exceptionClassName;
}