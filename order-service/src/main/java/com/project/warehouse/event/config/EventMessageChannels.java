package com.project.warehouse.event.config;


import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

// config class for event message channels
public interface EventMessageChannels {

    @Output("purchaseOrderEventOutboundChannel")
    SubscribableChannel purchaseOrderEventOutboundChannel();

}