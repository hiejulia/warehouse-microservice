package com.project.warehouse.hateoas;



import com.project.warehouse.model.PriceList;
import com.project.warehouse.rest.PriceListRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.UriTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Component
public class PriceListHateoasFactory {

    public Resource<PriceList> toResource(PriceList priceList) {
        Resource<PriceList> priceListResource = new Resource<>(priceList);

        Link selfLink = linkTo(PriceListRestController.class).slash(priceList.getId())
                .withSelfRel();

        Link goodsInPriceListLink = linkTo(PriceListRestController.class)
                .slash(priceList.getId()).slash("goods")
                .withRel("goods-list-in-price-list");

        priceListResource.add(selfLink, goodsInPriceListLink);
        return priceListResource;
    }

    public Resources<PriceList> toResources(List<PriceList> priceList) {
        Resources<PriceList> priceListResource = new Resources<>(priceList);

        UriTemplate priceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}",linkTo(PriceListRestController.class).toString()));
        Link priceListLink = new Link(priceListLinkUriTemplate, "price-list");

        UriTemplate goodsListInPriceListLinkUriTemplate = new UriTemplate(String.format("%s/{idPriceList}/goods",linkTo(PriceListRestController.class).toString()));
        Link goodsListInPriceListLink = new Link(goodsListInPriceListLinkUriTemplate, "goods-list-in-price-list");

        priceListResource.add(priceListLink,goodsListInPriceListLink);
        return priceListResource;
    }
}