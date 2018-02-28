package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.IdentityValidationErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface IdentityValidationErrorEventRepository extends CassandraRepository<IdentityValidationErrorEvent> {
}