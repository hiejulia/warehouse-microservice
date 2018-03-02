package com.project.warehouse.config;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.amqp.dsl.Amqp;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;


// auth server config
@Configuration
public class AuthServerAccountServiceBridgePipelineConfig {


    // autowired: RabbitMQTemplate
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public IntegrationFlow getUserDetailsIntegrationPipelineConfig(DirectChannel authServerAccountServiceBridgeInboundChannel,
                                                                   DirectChannel authServerAccountServiceBridgeOutboundChannel) {
        return IntegrationFlows.from(authServerAccountServiceBridgeInboundChannel)
                .handle(Amqp.outboundGateway(rabbitTemplate)
                        .routingKey("authServerAccountServiceBridgeInboundQueue")
                        .returnChannel(authServerAccountServiceBridgeOutboundChannel))
                .get();
    }
}