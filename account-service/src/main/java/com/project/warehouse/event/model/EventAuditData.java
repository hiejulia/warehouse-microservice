package com.project.warehouse.event.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.cassandra.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.Date;


@Data
@ToString(callSuper = true)
@UserDefinedType("eventAuditData") // TODO
public final class EventAuditData implements Serializable {
    // correlationId, userName, timeStamp

    private String correlationId;

    private String userName;

    private Date timeStamp;
}
