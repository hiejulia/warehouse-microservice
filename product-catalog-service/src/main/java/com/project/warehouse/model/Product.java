package com.project.warehouse.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
@EqualsAndHashCode(of = "id")
public class Product {

   // Product :
    @Id
    private String id;

    private String barCode;

    private String name;

    private String description;

    private String category;

    private String origin;

    @Version // locking database for update
    private Long version;


    private Map<String,String> productsAttribute;


}
