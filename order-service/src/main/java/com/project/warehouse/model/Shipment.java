package com.project.warehouse.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class Shipment implements Serializable {

    private String streetAddress;

    private String streetNumber;

    private String zip;

    private String shipmentDate;

}