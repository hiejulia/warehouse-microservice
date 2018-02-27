package com.project.warehouse.model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.io.Serializable;
import java.math.BigDecimal;



@Data @ToString @EqualsAndHashCode(of = "products")
public class ProductsInPriceList implements Serializable {
    @DBRef
    private Product products;

    private BigDecimal price;
}
