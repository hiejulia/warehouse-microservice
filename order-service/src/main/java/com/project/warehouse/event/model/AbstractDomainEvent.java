package com.project.warehouse.event.model;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.CassandraType;
import org.springframework.data.cassandra.mapping.PrimaryKey;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

// class to send event java pojo

@Data
@ToString(callSuper = true)
public abstract class AbstractDomainEvent implements Serializable {

    @JsonIgnore
    @PrimaryKey
    protected UUID id;

    private String correlationId;

    private String userName;

    private Date timeStamp;
}