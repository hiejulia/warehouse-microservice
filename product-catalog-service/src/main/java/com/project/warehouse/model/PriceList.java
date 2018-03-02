package com.project.warehouse.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;


@Data
@Document
@ToString
public class PriceList implements Serializable {

    @Id
    private String id;
    private String name;

    @Version
    private Long version;

    private List<ProductsInPriceList> productsInPriceList;
}