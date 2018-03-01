package com.project.warehouse.repository;

import com.project.warehouse.model.PurchaseOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface OrderRepository extends MongoRepository<PurchaseOrder, String> {

    @Query(value = "{'userName':?0}", fields="{ 'orderNumber' : 1, 'userName' : 1, 'status' : 1 }")
    Stream<PurchaseOrder> findPurchaseOrderIdByUserName(String userName);

    @Query(value = "{}", fields="{ 'orderNumber' : 1, 'userName' : 1, 'status' : 1 }")
    Stream<PurchaseOrder> findPurchaseOrder();

    Stream<PurchaseOrder> findByUserName(String userName);

    CompletableFuture<PurchaseOrder> findByUserNameAndOrderNumber(String userName, String orderNumber);
}