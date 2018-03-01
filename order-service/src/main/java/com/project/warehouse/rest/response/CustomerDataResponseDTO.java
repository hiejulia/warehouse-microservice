package com.project.warehouse.rest.response;


import com.project.warehouse.model.Customer;
import com.project.warehouse.model.CustomerContact;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;


@Data
@Builder
public class CustomerDataResponseDTO implements Serializable {

    // CustomerDataResponseDTO: customer, customerContact

    private Customer customer;

    private CustomerContact customerContact;
}