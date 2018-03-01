package com.project.warehouse.event.service;


import com.project.warehouse.event.factory.DomainEventFactory;
import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.event.model.PurchaseOrderErrorEvent;
import com.project.warehouse.event.model.PurchaseOrderEvent;
import com.project.warehouse.event.repository.PurchaseOrderErrorEventRepository;
import com.project.warehouse.event.repository.PurchaseOrderEventRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Data
@Service
public class EventDomainPubblishService {

    @Autowired
    private DomainEventFactory domainEventFactory;

    @Autowired
    public PurchaseOrderEventRepository purchaseOrderEventRepository;

    @Autowired
    public PurchaseOrderErrorEventRepository purchaseOrderErrorEventRepository;

    @Autowired
    private SubscribableChannel purchaseOrderEventOutboundChannel;

    public PurchaseOrderEvent publishPurchaseOrderEvent(String correlationId, String idPurchaseOrder,
                                                        String idProductCatalogId, String idGoodsInPurchaseOrder,
                                                        String customerUserName, EventTypeEnum type){
        PurchaseOrderEvent event = domainEventFactory.newPurchaseOrderEvent(correlationId, idPurchaseOrder,
                idProductCatalogId, idGoodsInPurchaseOrder,
                customerUserName, type);
        purchaseOrderEventRepository.save(event);
        purchaseOrderEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public PurchaseOrderErrorEvent publishPurchaseOrderErrorEvent(String correlationId, String idPurchaseOrder,
                                                                  String idProductCatalogId, String idGoodsInPurchaseOrder,
                                                                  String customerUserName, EventTypeEnum type,
                                                                  String message, Class exceptionClassName){
        PurchaseOrderErrorEvent event = domainEventFactory.newPurchaseOrderErrorEvent(correlationId, idPurchaseOrder,
                idProductCatalogId, idGoodsInPurchaseOrder,
                customerUserName, type,message, exceptionClassName);

        purchaseOrderErrorEventRepository.save(event);
        purchaseOrderEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

}