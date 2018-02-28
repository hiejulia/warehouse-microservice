package com.project.warehouse.event.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Map;



@Data
@Table
@ToString(callSuper = true)
public final class AccountValidationErrorEvent extends AbstractDomainEvent {


    // validation error event: validation error
    private Map<String,String> validationError;
}