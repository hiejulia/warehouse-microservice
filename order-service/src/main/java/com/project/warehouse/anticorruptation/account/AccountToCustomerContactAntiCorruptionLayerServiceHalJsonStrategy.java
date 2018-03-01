package com.project.warehouse.anticorruptation.account;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import com.project.warehouse.anticorruptation.AbstractAntiCorruptionLayerStrategy;
import com.project.warehouse.model.CustomerContact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;
import java.util.function.BiFunction;


@Slf4j
@Component
public class AccountToCustomerContactAntiCorruptionLayerServiceHalJsonStrategy extends AbstractAntiCorruptionLayerStrategy<CustomerContact> {

    @Override
    public CustomerContact traslate(String body) {
        log.info("body" + body);
        CustomerContact customerContact = null;
        try {
            customerContact  = new CustomerContact();
            ObjectNode node = (ObjectNode) objectMapper.readTree(body);

            JsonNode telephoneNumber = node.get("telephoneNumber");
            customerContact.setTelephone(String.format("%s %s%s",
                    getNodeValue.apply("countryPrefix",telephoneNumber),
                    getNodeValue.apply("prefix",telephoneNumber),
                    getNodeValue.apply("telephone",telephoneNumber)));

            customerContact.setMail(getNodeValue.apply("mail", node));
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return customerContact;
    }

    private BiFunction<String,JsonNode,String> getNodeValue = (key, node) ->
            Optional.ofNullable(node.get(key)).map(JsonNode::asText).orElse("");
}