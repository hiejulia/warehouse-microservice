package com.project.warehouse.event.repository;


import com.project.warehouse.event.model.AccountCreationEvent;
import org.springframework.data.cassandra.repository.CassandraRepository;


/**
 * AccountCreationEventRepo: extends : CasssandraRepo
 */
public interface AccountCreationEventRepository  extends CassandraRepository<AccountCreationEvent>{
}