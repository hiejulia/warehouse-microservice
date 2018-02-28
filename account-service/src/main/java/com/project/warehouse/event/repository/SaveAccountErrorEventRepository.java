package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.SaveAccountErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface SaveAccountErrorEventRepository extends CassandraRepository<SaveAccountErrorEvent> {

}