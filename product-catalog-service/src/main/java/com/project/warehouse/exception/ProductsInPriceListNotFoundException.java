package com.project.warehouse.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// PRODUCT IN PRICE LIST NOT FOUND EXCEPTION

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductsInPriceListNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "goods didn't found";

    public ProductsInPriceListNotFoundException(String msg) {
        super( msg);
    }

    public ProductsInPriceListNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}