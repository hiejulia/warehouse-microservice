package com.project.warehouse.event.repository;


import com.project.warehouse.event.model.PriceListEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

// cassandra
public interface PriceListEventRepository extends CassandraRepository<PriceListEvent> {

}