package com.project.warehouse.hateoas;


import com.project.warehouse.model.Product;

import com.project.warehouse.rest.ProductsRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;


@Component
public class ProductsHateoasFactory {

    public Resource<Product> toResource(Product goods) {
        Resource<Product> accountResource = new Resource<>(goods);

        Link selfLink = linkTo(ProductsRestController.class).slash(goods.getId())
                .withSelfRel();

        Link categoryAttributeLink = linkTo(ProductsRestController.class).slash(goods.getId()).slash("category-attribute")
                .withRel("category-attribute");

        accountResource.add(selfLink, categoryAttributeLink);
        return accountResource;
    }

    public Resources<Product> toResources(List<Product> goodsList) {
        Resources<Product> accountResource = new Resources<>(goodsList);

        Link selfRel = linkTo(ProductsRestController.class).withSelfRel();
        accountResource.add(selfRel);

        return accountResource;
    }
}