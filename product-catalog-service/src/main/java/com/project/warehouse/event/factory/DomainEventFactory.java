package com.project.warehouse.event.factory;


import com.datastax.driver.core.utils.UUIDs;

import com.project.warehouse.event.model.*;
import com.project.warehouse.security.SecurityUtils;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class DomainEventFactory {

    @Autowired
    private SecurityUtils securityUtils;

    public ProductsEvent newGoodsEvent(String correlationId, String idGoods, String name, String barCode,
                                       String category, EventTypeEnum type){
        ProductsEvent event = new ProductsEvent();
        event.setId(UUIDs.timeBased());
        event.setBarCode(barCode);
        event.setName(name);
        event.setCategory(category);
        event.setIdProducts(idGoods);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public ProductsErrorEvent newGoodsErrorEvent(String correlationId, String idGoods, String name, String barCode,
                                                 String category, EventTypeEnum type,
                                                 String message, Class exceptionClassName){
        ProductsErrorEvent event = new ProductsErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setBarCode(barCode);
        event.setName(name);
        event.setCategory(category);
        event.setIdProducts(idGoods);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }

    public PriceListEvent newPriceListEvent(String correlationId, String idPriceList,
                                            String name, EventTypeEnum type){
        PriceListEvent event = new PriceListEvent();
        event.setId(UUIDs.timeBased());
        event.setIdPriceList(idPriceList);
        event.setName(name);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        return event;
    }

    public PriceListErrorEvent newPriceListErrorEvent(String correlationId, String idPriceList,
                                                      String name, EventTypeEnum type,
                                                      String message, Class exceptionClassName){
        PriceListErrorEvent event = new PriceListErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setIdPriceList(idPriceList);
        event.setName(name);
        event.setType(type);
        event.setAuditData(newEventAuditData(correlationId));
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }


    private EventAuditData newEventAuditData(String correlationId){
        EventAuditData eventAuditData = new EventAuditData();

        eventAuditData.setCorrelationId(correlationId);
        eventAuditData.setUserName(securityUtils.getPrincipalUserName());
        eventAuditData.setTimeStamp(new Date());

        return eventAuditData;
    }
}