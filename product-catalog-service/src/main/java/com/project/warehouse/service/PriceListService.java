package com.project.warehouse.service;



import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.ProductsInPriceList;

import java.math.BigDecimal;
import java.util.List;



public interface PriceListService {
    PriceList createPriceList(PriceList priceList);

    List<PriceList> findPriceLists(boolean withoutGoodsInPriceList);

    PriceList findPriceList(String idPriceList);

    List<ProductsInPriceList> findGoodsListInPriceList(String idPriceList);
    ProductsInPriceList findGoodsInPriceList(String idPriceList, String idGoods);
    PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price);
    PriceList removeGoodsInPriceList(String idPriceList, String idGoods);

    PriceList updatePriceList(PriceList priceList);

    void deletePriceList(String idPriceList);
}