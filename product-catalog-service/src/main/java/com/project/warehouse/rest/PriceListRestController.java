package com.project.warehouse.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.math.BigDecimal;

@Data
@RestController
@RequestMapping("/price-list")
public class PriceListRestController {

    // GET get all price list

    // GET get price list by id



    // GET get all products by price


    // POST create new price list


    // PATCH update products in price list


    // DELETE remove product in price list



    // PUT update price list



    // DELETE
}
