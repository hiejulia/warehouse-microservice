package com.project.warehouse.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Delivery implements Serializable {

    private String personDeliveryName;

    private Date deliveryDate;

}