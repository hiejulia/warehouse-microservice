package com.project.warehouse.event.service;


import com.project.warehouse.event.factory.DomainEventFactory;
import com.project.warehouse.event.model.*;
import com.project.warehouse.event.repository.PriceListErrorEventRepository;
import com.project.warehouse.event.repository.PriceListEventRepository;
import com.project.warehouse.event.repository.ProductsErrorEventRepository;
import com.project.warehouse.event.repository.ProductsEventRepository;
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
    private ProductsEventRepository goodsEventRepository;

    @Autowired
    private ProductsErrorEventRepository goodsErrorEventRepository;

    @Autowired
    private PriceListEventRepository priceListEventRepository;

    @Autowired
    private PriceListErrorEventRepository priceListErrorEventRepository;

    @Autowired
    private SubscribableChannel goodsEventOutboundChannel;

    @Autowired
    private SubscribableChannel priceListEventOutboundChannel;

    public ProductsEvent publishGoodsEvent(String correlationId, String idGoods, String name, String barCode,
                                           String category, EventTypeEnum type){
        ProductsEvent event = domainEventFactory.newGoodsEvent(correlationId, idGoods, name, barCode, category, type);
        goodsEventRepository.save(event);
        goodsEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

    public ProductsErrorEvent publishGoodsErrorEvent(String correlationId, String idGoods, String name, String barCode,
                                                     String category, EventTypeEnum type,
                                                     String message, Class exceptionClassName){
        ProductsErrorEvent event = domainEventFactory.newGoodsErrorEvent(correlationId, idGoods, name, barCode, category, type,message,exceptionClassName);
        goodsErrorEventRepository.save(event);
        goodsEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public PriceListEvent publishPriceListEvent(String correlationId, String idPriceList,
                                                String name, EventTypeEnum type){
        PriceListEvent event = domainEventFactory.newPriceListEvent(correlationId, idPriceList, name, type);
        priceListEventRepository.save(event);
        priceListEventOutboundChannel.send(MessageBuilder.withPayload(event).build());
        return event;
    }

    public PriceListErrorEvent publishPriceListErrorEvent(String correlationId, String idPriceList,
                                                          String name, EventTypeEnum type,
                                                          String message, Class exceptionClassName){
        PriceListErrorEvent event = domainEventFactory.newPriceListErrorEvent(correlationId, idPriceList, name, type, message, exceptionClassName);
        priceListErrorEventRepository.save(event);
        priceListEventOutboundChannel.send(MessageBuilder.withPayload(event).build());

        return event;
    }

}