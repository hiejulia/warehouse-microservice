package com.project.warehouse.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;

@Data
@RestController
@RequestMapping("/products")
public class ProductsRestController {
 // autowired: ProductService, productHateoasFactory

    // GET findAllProducts


    // GET findProductById

    // POST create new product


    // POST save product attribute

    // PUT update existing product


    // DELETE delete product


    // DELETE remove product attribute
}
