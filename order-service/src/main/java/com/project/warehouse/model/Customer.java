package com.project.warehouse.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class Customer implements Serializable {

    private String firstName;

    private String lastName;

    private String taxCode;
}