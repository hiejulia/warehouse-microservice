package com.project.warehouse.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Document // SAVE DATABASE
@ToString
public class PurchaseOrder implements Serializable {

    @Id
    public String orderNumber;
    public Date orderDate;
    public String userName;

    @Version
    private Long version;

    private PurchaseOrderStatusEnum status;

    private Customer customer;
    private CustomerContact customerContact;
    private List<Product> goodsList;
    private Shipment shipment;
    private Delivery delivery;
    private BigDecimal total;
}