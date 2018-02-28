package com.project.warehouse.event.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Data
@ToString(callSuper = true) // TODO
public abstract class AbstractDomainEvent implements Serializable {
    // ID, EventAuditData


    @JsonIgnore
    @PrimaryKey
    protected UUID id;

    @CassandraType(type = DataType.Name.UDT, userTypeName = "eventAuditData") // TODO
    protected EventAuditData auditData;

}
