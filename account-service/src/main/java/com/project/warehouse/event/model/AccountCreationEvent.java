package com.project.warehouse.event.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;


@Data
@Table
@ToString(callSuper = true)
public final class AccountCreationEvent extends AbstractDomainEvent {

    // creation : userName

    private String userName;
}

