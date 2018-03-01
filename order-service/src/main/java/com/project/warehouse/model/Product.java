package com.project.warehouse.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

@Data
@ToString
@EqualsAndHashCode(of = {"id","priceListId"})
public class Product implements Serializable {

    private String id;

    private String priceListId;

    private String name;

    private String barCode;

    private BigDecimal price;

    private int quantity;

    private Map<String, String> productsAttribute;

}