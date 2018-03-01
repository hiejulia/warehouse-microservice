package com.project.warehouse.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClientHttpRequestFactory;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;


@Configuration
@RibbonClient("accountIntegrationServiceConfig")
public class AccountIntegrationServiceConfig {

    @Bean
    @LoadBalanced
    public OAuth2RestTemplate accountIntegrationServiceRestTemplate(SpringClientFactory springClientFactory,
                                                                    OAuth2ProtectedResourceDetails authorizationCodeResourceDetails,
                                                                    OAuth2ClientContext context){
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails, context);
        oAuth2RestTemplate.setRequestFactory(new RibbonClientHttpRequestFactory(springClientFactory));
        return oAuth2RestTemplate;
    }
}