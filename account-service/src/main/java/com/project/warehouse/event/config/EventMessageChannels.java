package com.project.warehouse.event.config;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.SubscribableChannel;

public interface EventMessageChannels {

    @Output("accountEventOutboundChannel")
    SubscribableChannel accountEventOutboundChannel();

}