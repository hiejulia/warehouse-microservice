package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.ProductsEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface ProductsEventRepository extends CassandraRepository<ProductsEvent> {
}