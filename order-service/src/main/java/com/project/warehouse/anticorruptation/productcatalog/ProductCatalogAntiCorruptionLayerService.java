package com.project.warehouse.anticorruptation.productcatalog;


import com.project.warehouse.anticorruptation.AbstractAntiCorruptionLayerService;
import com.project.warehouse.anticorruptation.AntiCorruptionLayerStrategy;
import com.project.warehouse.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Service
public class ProductCatalogAntiCorruptionLayerService extends AbstractAntiCorruptionLayerService {

    private Map<String, AntiCorruptionLayerStrategy> productCatalogAntiCorruptationRegistry;

    @Autowired
    private ProductCatalogAntiCorruptionLayerServiceHalJsonStrategy productCatalogAntiCorruptionLayerServiceHalJsonStrategy;

    @PostConstruct
    public void init(){
        productCatalogAntiCorruptationRegistry = new HashMap<>();
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_VALUE,productCatalogAntiCorruptionLayerServiceHalJsonStrategy);
        productCatalogAntiCorruptationRegistry.put(MediaType.APPLICATION_JSON_UTF8_VALUE,productCatalogAntiCorruptionLayerServiceHalJsonStrategy);
    }

    public Product newGoods(String goods, String mediaType){
        return (Product) Optional.ofNullable(productCatalogAntiCorruptationRegistry.get(mediaType))
                .map(anticCorruptationLayerStrategy -> anticCorruptationLayerStrategy.traslate(goods))
                .orElse(null);
    }
}