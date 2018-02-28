package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.RemoveAccountEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;



public interface RemoveAccountEventRepository extends CassandraRepository<RemoveAccountEvent> {

}