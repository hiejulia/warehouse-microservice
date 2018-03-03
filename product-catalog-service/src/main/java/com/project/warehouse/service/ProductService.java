package com.project.warehouse.service;




import com.project.warehouse.model.Product;

import java.util.List;

public interface ProductService {
    Product createProducts(Product goods);

    List<Product> findProductsList();
    List<Product> findProductsList(String category);

    Product findProducts(String idGoods);

    Product saveProductsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue);
    Product removeProductsAttributeValue(String idGoods, String goodsAttributeKey);

    Product updateProducts(Product products);

    void deleteProducts(String idGoods);
}