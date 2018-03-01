package com.project.warehouse.anticorruptation.account;


import com.fasterxml.jackson.databind.node.ObjectNode;

import com.project.warehouse.anticorruptation.AbstractAntiCorruptionLayerStrategy;
import com.project.warehouse.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
@Component
public class AccountToCustomerAntiCorruptionLayerServiceHalJsonStrategy extends AbstractAntiCorruptionLayerStrategy<Customer> {

    @Override
    public Customer traslate(String body) {
        Customer customer = null;
        try {
            customer  = new Customer();
            ObjectNode node = (ObjectNode) objectMapper.readTree(body);

            customer.setFirstName(node.get("firstName").asText());
            customer.setLastName(node.get("lastName").asText());
            customer.setTaxCode(node.get("taxCode").asText());
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return customer;
    }

}