package com.project.warehouse.service;


import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.event.model.PriceListErrorEvent;
import com.project.warehouse.event.model.ProductsErrorEvent;
import com.project.warehouse.event.service.EventDomainPubblishService;
import com.project.warehouse.exception.PriceListNotFoundException;
import com.project.warehouse.exception.ProductsNotFoundException;
import com.project.warehouse.exception.SavePriceListException;
import com.project.warehouse.exception.SaveProductsException;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.model.ProductsInPriceList;
import com.project.warehouse.repository.PriceListRepository;
import com.project.warehouse.repository.ProductRepository;
import com.project.warehouse.validator.PriceListDataValidator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.function.Function;


@Data
public abstract class AbstractService {

    @Autowired
    protected PriceListDataValidator priceListDataValidator;

    @Autowired
    protected EventDomainPubblishService eventDomainPubblishService;

    @Autowired
    protected PriceListRepository priceListRepository;

    @Autowired
    protected ProductRepository productRepository;

    protected Function<Product, Map<String, String>> getSafeGoodsAttribute =
            (goods) -> Optional.ofNullable(goods.getProductsAttribute()).orElse(new HashMap<>());

    protected Function<PriceList, List<ProductsInPriceList>> getSafeGoodsInPriceList =
            (priceList) -> Optional.ofNullable(priceList.getProductsInPriceList()).orElse(new ArrayList<>());


    protected Product doSaveGoodsData(String correlationId, Product goods) {
        Product goodsAux;
        ProductsErrorEvent goodsErrorEvent;
        try{
            goodsAux = productRepository.save(goods);

            eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(),
                    goods.getName(),goods.getBarCode(),goods.getCategory(), EventTypeEnum.SAVE);
        } catch (Exception e){
            e.printStackTrace();
            goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, goods.getId(), goods.getName(),goods.getBarCode(),
                    goods.getCategory(), EventTypeEnum.SAVE, SaveProductsException.DEFAULT_MESSAGE, SaveProductsException.class);
            throw  new SaveProductsException(goodsErrorEvent, SaveProductsException.DEFAULT_MESSAGE);
        }

        return goodsAux;
    }

    protected PriceList doSavePriceListData(String correlationId, PriceList priceList) {
        PriceList priceListAux;
        PriceListErrorEvent priceListErrorEvent ;
        try{
            priceListAux = priceListRepository.save(priceList);

            eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                    priceList.getName(),EventTypeEnum.SAVE);
        } catch (Exception e){
            priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, priceList.getId(), priceList.getName(),EventTypeEnum.SAVE, SavePriceListException.DEFAULT_MESSAGE, SavePriceListException.class);
            throw  new SavePriceListException(priceListErrorEvent, SavePriceListException.DEFAULT_MESSAGE);
        }

        return priceListAux;
    }

    protected void doCheckGoodsExist(String correlationId, String idGoods) {
        Product goodsAux;
        Function<String, ProductsNotFoundException> f = userNameAux -> {
            ProductsErrorEvent goodsErrorEvent = eventDomainPubblishService.publishGoodsErrorEvent(correlationId, idGoods,
                    null, null, null, EventTypeEnum.READ, ProductsNotFoundException.DEFAULT_MESSAGE, ProductsNotFoundException.class);
            return new ProductsNotFoundException(goodsErrorEvent, ProductsNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            goodsAux =  productRepository.findOne(idGoods);
            if(goodsAux== null){
                throw f.apply(idGoods);
            }
        } catch (Exception e){
            throw f.apply(idGoods);
        }
    }

    protected void doCheckPriceListExist(String correlationId, String idPriceList) {
        PriceList priceListAux;
        Function<String, PriceListNotFoundException> f = userNameAux -> {
            PriceListErrorEvent priceListErrorEvent = eventDomainPubblishService.publishPriceListErrorEvent(correlationId, idPriceList,
                    null, EventTypeEnum.READ, PriceListNotFoundException.DEFAULT_MESSAGE, ProductsNotFoundException.class);
            return new PriceListNotFoundException(priceListErrorEvent, PriceListNotFoundException.DEFAULT_MESSAGE);
        };

        try{
            priceListAux =  priceListRepository.findOne(idPriceList);
            if(priceListAux== null){
                throw f.apply(idPriceList);
            }
        } catch (Exception e){
            throw f.apply(idPriceList);
        }
    }
}