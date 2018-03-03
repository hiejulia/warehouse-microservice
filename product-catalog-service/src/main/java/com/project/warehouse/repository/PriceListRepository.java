package com.project.warehouse.repository;

import com.project.warehouse.model.PriceList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface PriceListRepository extends MongoRepository<PriceList, String> {

    // get price by name

    PriceList findByName(String name);

    // find all without products in price list
    @Query(value = "{}",fields = "{productsInPriceList : 0}")
    List<PriceList> findAllWithoutProductsInPriceList();
}