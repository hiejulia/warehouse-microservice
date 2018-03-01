package com.project.warehouse.model;


import lombok.Data;
import lombok.ToString;

import java.io.Serializable;


@Data
@ToString
public class CustomerContact implements Serializable {

    private String telephone;

    private String mail;
}