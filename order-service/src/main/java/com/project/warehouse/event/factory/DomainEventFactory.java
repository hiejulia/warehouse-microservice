package com.project.warehouse.event.factory;


import com.datastax.driver.core.utils.UUIDs;

import com.project.warehouse.event.model.AbstractDomainEvent;
import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.event.model.PurchaseOrderErrorEvent;
import com.project.warehouse.event.model.PurchaseOrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class DomainEventFactory {

    @Autowired
    private SecurityUtils securityUtils;

    public PurchaseOrderEvent newPurchaseOrderEvent(String correlationId, String idPurchaseOrder,
                                                    String idProductCatalog, String idGoodsInPurchaseOrder,
                                                    String customerUserName, EventTypeEnum type){
        PurchaseOrderEvent event = new PurchaseOrderEvent();
        event.setId(UUIDs.timeBased());
        event.setCustomerUserName(customerUserName);
        event.setIdProductCatalog(idProductCatalog);
        event.setIdPurchaseOrder(idPurchaseOrder);
        event.setIdProductsInPurchaseOrder(idGoodsInPurchaseOrder);
        event.setType(type);

        newEventAuditData(event,correlationId);
        return event;
    }

    public PurchaseOrderErrorEvent newPurchaseOrderErrorEvent(String correlationId, String idPurchaseOrder,
                                                              String idProductCatalog, String idGoodsInPurchaseOrder,
                                                              String customerUserName, EventTypeEnum type,
                                                              String message, Class exceptionClassName){
        PurchaseOrderErrorEvent event = new PurchaseOrderErrorEvent();
        event.setId(UUIDs.timeBased());
        event.setCustomerUserName(customerUserName);
        event.setIdProductCatalog(idProductCatalog);
        event.setIdPurchaseOrder(idPurchaseOrder);
        event.setIdProductsInPurchaseOrder(idGoodsInPurchaseOrder);

        event.setType(type);
        newEventAuditData(event,correlationId);
        event.setExceptionClassName(exceptionClassName.getName());
        event.setMessage(message);
        return event;
    }


    private void newEventAuditData(AbstractDomainEvent eventAuditData, String correlationId) {

        eventAuditData.setCorrelationId(correlationId);
        eventAuditData.setUserName(securityUtils.getPrincipalUserName());
        eventAuditData.setTimeStamp(new Date());
    }
}