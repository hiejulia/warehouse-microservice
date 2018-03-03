package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;


// Price list event
@Data
@Table
@ToString
public class PriceListEvent extends AbstractDomainEvent {

    private String idPriceList;

    private String name;

    private EventTypeEnum type;
}