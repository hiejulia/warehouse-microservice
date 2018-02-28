package com.project.warehouse.event.repository;


import com.project.warehouse.event.model.RemoveAccountErrorEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


public interface RemoveAccountErrorEventRepository extends CassandraRepository<RemoveAccountErrorEvent> {

}