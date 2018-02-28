package com.project.warehouse.model;

import lombok.Data;

import javax.persistence.Embeddable;


@Data
@Embeddable
public class Address {
    private String street;

    private String streenNumber;

    private String zip;

    private String country;

    private String region;

    private String city;
}