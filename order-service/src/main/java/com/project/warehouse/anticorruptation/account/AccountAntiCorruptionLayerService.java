package com.project.warehouse.anticorruptation.account;



import com.project.warehouse.anticorruptation.AbstractAntiCorruptionLayerService;
import com.project.warehouse.anticorruptation.AntiCorruptionLayerStrategy;
import com.project.warehouse.model.Customer;
import com.project.warehouse.model.CustomerContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class AccountAntiCorruptionLayerService extends AbstractAntiCorruptionLayerService {

    private Map<String, AntiCorruptionLayerStrategy> customerAntiCorruptationRegistry;
    private Map<String, AntiCorruptionLayerStrategy> customerContactAnticorruptationRegistry;

    @Autowired
    private AccountToCustomerAntiCorruptionLayerServiceHalJsonStrategy accountToCustomerAntiCorruptionLayerServiceHalJsonStrategy;

    @Autowired
    private AccountToCustomerContactAntiCorruptionLayerServiceHalJsonStrategy accountToCustomerContactAntiCorruptionLayerServiceHalJsonStrategy;

    @PostConstruct
    public void init(){
        customerAntiCorruptationRegistry = new HashMap<>();
        customerAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, accountToCustomerAntiCorruptionLayerServiceHalJsonStrategy);
        customerAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE, accountToCustomerAntiCorruptionLayerServiceHalJsonStrategy);

        customerContactAnticorruptationRegistry = new HashMap<>();
        customerContactAnticorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE, accountToCustomerContactAntiCorruptionLayerServiceHalJsonStrategy);
        customerContactAnticorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE, accountToCustomerContactAntiCorruptionLayerServiceHalJsonStrategy);
    }

    public Customer newCustomer(String customer, String mediaType){
        return (Customer) Optional.ofNullable(customerAntiCorruptationRegistry.get(mediaType))
                .map(antiCorruptationLayerStrategy -> antiCorruptationLayerStrategy.traslate(customer))
                .orElse(null);
    }

    public CustomerContact newCustomerContact(String gustomerContact, String mediaType){
        return (CustomerContact) Optional.ofNullable(customerContactAnticorruptationRegistry.get(mediaType))
                .map(antiCorruptationLayerStrategy -> antiCorruptationLayerStrategy.traslate(gustomerContact))
                .orElse(null);
    }
}