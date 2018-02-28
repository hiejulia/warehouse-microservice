package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;


@Data
@Table
@ToString
public class PriceListErrorEvent extends BaseErrorEvent {
    private String idPriceList;
    private String name;

    private EventTypeEnum type;
}