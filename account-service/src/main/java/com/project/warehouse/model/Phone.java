package com.project.warehouse.model;


import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Phone {
    private String countryPrefix;

    private String prefix;

    private String telephone;
}