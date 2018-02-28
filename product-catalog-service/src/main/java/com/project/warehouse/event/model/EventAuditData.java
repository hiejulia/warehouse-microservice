package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.Date;


/**
 * EventAuditData: correlationId, userName, time
 */
@Data
@ToString(callSuper = true)
@UserDefinedType("eventAuditData")
public final class EventAuditData implements Serializable {

    private String correlationId;

    private String userName;

    private Date timeStamp;
}