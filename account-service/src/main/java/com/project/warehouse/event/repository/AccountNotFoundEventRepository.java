package com.project.warehouse.event.repository;


import com.project.warehouse.event.model.AccountNotFoundEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;

// extends CassandraRepository

public interface AccountNotFoundEventRepository extends CassandraRepository<AccountNotFoundEvent>{

}