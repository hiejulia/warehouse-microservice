package com.project.warehouse.event.repository;



import com.project.warehouse.event.model.ChangeAccountPasswordEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;



public interface ChangeAccountPasswordEventRepository extends CassandraRepository<ChangeAccountPasswordEvent> {

}