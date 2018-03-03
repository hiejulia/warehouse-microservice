package com.project.warehouse.hateoas;



import com.project.warehouse.model.ProductsInPriceList;
import com.project.warehouse.rest.PriceListRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class ProductsInPriceListHateoasFactory {

    public Resource<ProductsInPriceList> toResource(String idPriceList, ProductsInPriceList goodsInPriceList) {
        Resource<ProductsInPriceList> priceListResource = new Resource<>(goodsInPriceList);

        Link selfLink = linkTo(PriceListRestController.class)
                .slash(idPriceList).slash("goods")
                .slash(goodsInPriceList.getProducts().getId())
                .withSelfRel();

        Link priceListLink = linkTo(PriceListRestController.class)
                .slash(idPriceList).withRel("price-list");

        priceListResource.add(selfLink, priceListLink);
        return priceListResource;
    }

    public Resources<ProductsInPriceList> toResources(String idPriceList, List<ProductsInPriceList> goodsInPriceList) {
        Resources<ProductsInPriceList> priceListResource = new Resources<>(goodsInPriceList);

        Link selfLink = linkTo(PriceListRestController.class)
                .slash(idPriceList).slash("goods")
                .withSelfRel();

        Link priceListLink = linkTo(PriceListRestController.class)
                .slash(idPriceList).withRel("price-list");

        priceListResource.add(selfLink, priceListLink);

        return priceListResource;
    }
}