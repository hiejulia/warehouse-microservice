package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.PurchaseOrderEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

// extends cassandra
public interface PurchaseOrderEventRepository extends CassandraRepository<PurchaseOrderEvent> {
}