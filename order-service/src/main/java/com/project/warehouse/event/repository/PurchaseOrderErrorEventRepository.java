package com.project.warehouse.event.repository;


import com.project.warehouse.event.model.PurchaseOrderErrorEvent;
import org.springframework.data.mongodb.repository.MongoRepository;


// extends mongodb
public interface PurchaseOrderErrorEventRepository extends MongoRepository<PurchaseOrderErrorEvent, String> {
}