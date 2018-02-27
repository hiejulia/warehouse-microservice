package com.project.warehouse.validator;


import com.project.warehouse.model.Price;
import com.project.warehouse.model.Product;
import com.project.warehouse.model.ProductsInPriceList;

public interface PriceListDataValidator {

    void validate(String correlationId, Price priceList) throws PriceListValidationException;
    void validate(String correlationId, ProductsInPriceList productsInPriceList) throws PriceListValidationException;
    void validate(String correlationId, Product products) throws GoodsValidationException;



}