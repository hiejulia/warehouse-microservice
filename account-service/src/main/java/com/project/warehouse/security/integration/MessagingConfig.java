package com.project.warehouse.security.integration;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessagingConfig {

    // queue : name: authServerAccountServiceBridgeInboundQueue
    @Bean("authServerAccountServiceBridgeInboundQueue")
    public Queue authServerAccountServiceBridgeInboundQueue(){
        return new Queue("authServerAccountServiceBridgeInboundQueue", false, false, true);
    }

}