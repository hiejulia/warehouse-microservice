package com.project.warehouse.exception;


import lombok.Getter;

@Getter
public abstract class AbstractException extends RuntimeException {

    public AbstractException(String msg) {
        super(msg);
    }

    public AbstractException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public abstract String getDefaultMessage();
}