package com.project.warehouse.rest.rest;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Data
@RestController
@RequestMapping("/purchase-order")
public class CustomerRestFullEndPoint extends AbstractPurchaseOrderRestFullEndPoint {

    @Autowired
    private CustomerHateoasFactory customerHateoasFactory;

    @GetMapping("/{orderNumber}/customer")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity getCustomerDataPuchaseOrder(@PathVariable String orderNumber){
        PurchaseOrder purchaseOrder = purchaseOrderService.findPurchaseOrder(securityUtils.getPrincipalUserName(), orderNumber);

        CustomerDataResponseDTO customerDataResponseDTO =
                CustomerDataResponseDTO.builder()
                        .customer(purchaseOrder.getCustomer())
                        .customerContact(purchaseOrder.getCustomerContact()).build();

        return ResponseEntity.ok(customerHateoasFactory.toResource(orderNumber, customerDataResponseDTO));
    }

    @PatchMapping("/{orderNumber}/customer")
    @PreAuthorize("hasRole('ROLE_USER')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity customerDataPuchaseOrder(@PathVariable String orderNumber, Principal principal){
        purchaseOrderService.withCustomerAndCustomerContact(orderNumber, principal.getName(), null, null);
        return ResponseEntity.noContent().build();
    }

}