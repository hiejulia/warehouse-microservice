package com.project.warehouse.rest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import com.project.warehouse.hateoas.ProductsHateoasFactory;
import com.project.warehouse.model.Product;
import com.project.warehouse.service.ProductService;
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

    @Autowired
    private ProductService goodsService;

    @Autowired
    private ProductsHateoasFactory goodsHateoasFactory;

    @GetMapping
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findAllGoods(){
        return ResponseEntity.ok(goodsHateoasFactory.toResources(goodsService.findProductsList()));
    }

    @GetMapping("/{idGoods}")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity findGoods(@PathVariable String idGoods){
        return ResponseEntity.ok(goodsHateoasFactory.toResource(goodsService.findProducts(idGoods)));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity createGoods(@RequestBody Product goods){
        Product goodsAux = goodsService.createProducts(goods);
        URI findGoods = MvcUriComponentsBuilder.fromMethodName(ProductsRestController.class,
                "findGoods", goodsAux.getId()).build().toUri();
        return ResponseEntity.created(findGoods).build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PatchMapping("/{idGoods}/category-attribute")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity saveGoodsAttributeValue(@PathVariable String idGoods, @RequestBody HashMap<String,String> goods){
        goods.entrySet().forEach(entry -> goodsService.saveProductsAttributeValue(idGoods,entry.getKey(), entry.getValue()));
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idGoods}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity updateGoods(@PathVariable String idGoods, @RequestBody Product goods){
        goods.setId(idGoods);
        goodsService.updateProducts(goods);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{idGoods}")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity deleteGoods(@PathVariable String idGoods){
        goodsService.deleteProducts(idGoods);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping("/{idGoods}/category-attribute")
    @HystrixCommand(commandProperties = {@HystrixProperty(name="execution.isolation.strategy", value="SEMAPHORE")})
    public ResponseEntity removeGoodsAttributeValue(@PathVariable String idGoods, @PathVariable String goodsAttributeKey){
        goodsService.removeProductsAttributeValue(idGoods, goodsAttributeKey);
        return ResponseEntity.noContent().build();
    }
}