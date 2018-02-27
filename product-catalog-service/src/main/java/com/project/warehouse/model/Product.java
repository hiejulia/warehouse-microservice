package com.project.warehouse.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
@TypeAlias("product")
@ToString
@EqualsAndHashCode(of = "id")
public class Product {

   // Product :
    @Id
    private String id;

    @org.springframework.data.mongodb.core.index.Indexed(name = "barCode",unique = true)
    private String barCode;


    @Field("name")
    private String name;

    private String description;


//    @DBRef
    private String category;

    private String origin;

    @Version // locking database for update
    private Long version;


    private Map<String,String> productsAttribute;


}
