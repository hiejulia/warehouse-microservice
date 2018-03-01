package com.project.warehouse.event.model;


import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.Table;


@Data
@Table
@ToString
public class PurchaseOrderEvent extends AbstractDomainEvent {

    private String idPurchaseOrder;

    private String idProductCatalog;

    private String idProductsInPurchaseOrder;

    private String customerUserName;

    private EventTypeEnum type;
}