package com.project.warehouse.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductsInPriceListNotFoundException extends AbstractException {

    public static final String DEFAULT_MESSAGE = "Products in price list not found";

    public ProductsInPriceListNotFoundException(String msg) {
        super( msg);
    }

    public ProductsInPriceListNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }

    @Override
    public String getDefaultMessage() {
        return DEFAULT_MESSAGE;
    }

}