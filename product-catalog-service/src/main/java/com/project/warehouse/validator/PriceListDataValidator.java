package com.project.warehouse.validator;


import com.project.warehouse.exception.PriceListValidationException;
import com.project.warehouse.exception.ProductsValidationException;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.model.ProductsInPriceList;

public interface PriceListDataValidator {

    void validate(String correlationId, PriceList priceList) throws PriceListValidationException;
    void validate(String correlationId, ProductsInPriceList productsInPriceList) throws PriceListValidationException;
    void validate(String correlationId, Product products) throws ProductsValidationException;



}