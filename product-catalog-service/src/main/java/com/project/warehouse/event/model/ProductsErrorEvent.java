package com.project.warehouse.event.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;


@Data
@Table
@ToString
public class ProductsErrorEvent extends BaseErrorEvent {
    private String idProducts;

    private String name;

    private String barCode;

    private String category;

    private EventTypeEnum type;
}