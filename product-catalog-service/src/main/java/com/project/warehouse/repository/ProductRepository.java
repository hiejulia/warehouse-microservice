package com.project.warehouse.repository;



import com.project.warehouse.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ProductRepository extends MongoRepository<Product, String> {

    // find Product by category
    List<Product> findByCategory(String category);

    // find product by id
    Product findById(String id);
}