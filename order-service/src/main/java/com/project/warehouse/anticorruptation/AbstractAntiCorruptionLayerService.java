package com.project.warehouse.anticorruptation;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public abstract class AbstractAntiCorruptionLayerService {

    @Autowired
    protected ObjectMapper objectMapper;
    // object mapper

}