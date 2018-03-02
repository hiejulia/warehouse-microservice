package com.project.warehouse.validator;


import com.project.warehouse.exception.PriceListValidationException;
import com.project.warehouse.exception.ProductsValidationException;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.model.ProductsInPriceList;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;


@Data
@Component
public class PriceListDataValidatorImpl implements PriceListDataValidator {

    @Autowired
    protected MessageSource messageSource;

    @Override
    public void validate(String correlationId, PriceList priceList) throws PriceListValidationException {
        Map<String,String> errors = new HashMap<>();
        validateNotNullAndNotEmpty("priceListName", priceList.getName(), "PriceListDataValidator.PriceList.name", errors);

/*        List<String> priceErrors = Optional.ofNullable(priceList.getGoodsInPriceList())
                .map(goodsInPriceListList -> goodsInPriceListList.stream()
                        .filter(priceIsInvalid)
                        .map(this::goodsPriceError)
                        .collect(Collectors.toList())).orElse(new ArrayList<>());
        if(priceErrors.size() > 0){
            errors.put("priceListGoodsPrice", priceErrors.toString());
        }*/
    }

    @Override
    public void validate(String correlationId, ProductsInPriceList goodsInPriceList) throws PriceListValidationException {
        goodsPriceError(goodsInPriceList);
    }


    @Override
    public void validate(String correlationId, Product goods) throws ProductsValidationException {
        Map<String,String> errors = new HashMap<>();
        validateNotNullAndNotEmpty("goodsName", goods.getName(), "PriceListDataValidator.Goods.name", errors);
        validateNotNullAndNotEmpty("goodsBarCode", goods.getBarCode(), "PriceListDataValidator.Goods.barCode", errors);

    }


    private void validateNotNullAndNotEmpty(String key,String value,String validationMessageKey, Map<String,String> errors){
        if (value == null || "".equals(value)){
            errors.put(key, messageSource.getMessage(validationMessageKey,new Object[]{}, Locale.ENGLISH));
        }
    }

    Predicate<ProductsInPriceList> priceIsInvalid = item -> item.getPrice()!= null || item.getPrice().doubleValue() < 0;
    private String goodsPriceError(ProductsInPriceList itemAux){
        return messageSource.getMessage("PriceListDataValidator.Goods.invalidPrice",
                new Object[]{itemAux.getProducts().getName(), itemAux.getProducts().getBarCode()}, Locale.ENGLISH);
    }
}