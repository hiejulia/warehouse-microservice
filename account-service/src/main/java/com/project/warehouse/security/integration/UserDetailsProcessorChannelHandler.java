package com.project.warehouse.security.integration;



import com.project.warehouse.security.AccountUserDetailsService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


@Data
@Slf4j
@Component
public class UserDetailsProcessorChannelHandler {

    @Autowired
    private AccountUserDetailsService accountUserDetailsService;

    @RabbitListener(queues = "authServerAccountServiceBridgeInboundQueue")
    public UserDetails getUserDetails(String userName){
        log.info("userName: " + userName);
        UserDetails userDetails = null;

        try {
            userDetails = accountUserDetailsService.loadUserByUsername(userName);
        } catch (Exception e){
            log.error("user didn't found");
        }
        return userDetails;
    }
}