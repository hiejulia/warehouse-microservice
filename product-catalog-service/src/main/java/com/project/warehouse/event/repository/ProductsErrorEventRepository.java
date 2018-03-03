package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.ProductsErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface ProductsErrorEventRepository extends CassandraRepository<ProductsErrorEvent> {
}