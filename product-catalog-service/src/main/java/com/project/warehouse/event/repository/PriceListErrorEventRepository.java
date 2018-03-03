package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.PriceListErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

// cassandra
public interface PriceListErrorEventRepository extends CassandraRepository<PriceListErrorEvent> {
}