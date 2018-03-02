package com.project.warehouse.integration;


import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.security.core.userdetails.UserDetails;


@MessagingGateway
public interface LogInRequestGateway {

    @Gateway(requestChannel = "authServerAccountServiceBridgeInboundChannel",
            replyChannel = "authServerAccountServiceBridgeOutboundChannel",
            replyTimeout = 60*1000)
    UserDetails getPrincipleByUSerName(String userName);
    // get user name principle :
}