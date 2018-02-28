package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.AccountValidationErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;



public interface AccountValidationErrorEventRepository extends CassandraRepository<AccountValidationErrorEvent> {
}