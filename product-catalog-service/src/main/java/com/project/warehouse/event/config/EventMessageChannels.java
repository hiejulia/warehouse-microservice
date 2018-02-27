package com.project.warehouse.event.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

//Event message channel config class
public interface EventMessageChannels {

    //  add subscribe and add 2 queue to subscribe

    // PRODUCT
    @Output("productsEventOutboundChannel")
    SubscribableChannel productsEventOutboundChannel();

    // PRICE
    @Output("priceListEventOutboundChannel")
    SubscribableChannel priceListEventOutboundChannel();


}
