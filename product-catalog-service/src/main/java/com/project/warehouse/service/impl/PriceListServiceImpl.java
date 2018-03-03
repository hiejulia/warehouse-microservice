package com.project.warehouse.service.impl;



import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.exception.ProductsInPriceListNotFoundException;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.model.ProductsInPriceList;
import com.project.warehouse.service.AbstractService;
import com.project.warehouse.service.PriceListService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceListServiceImpl extends AbstractService implements PriceListService {

    @Override
    public PriceList createPriceList(PriceList priceList) {
        String correlationId = UUID.randomUUID().toString();
        priceList.setProductsInPriceList(Optional.ofNullable(priceList.getProductsInPriceList()).orElse(new ArrayList<>()));
        priceListDataValidator.validate(correlationId, priceList);
        PriceList save = doSavePriceListData(correlationId, priceList);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.CREATE);
        return save;
    }


    public List<PriceList> findPriceLists(boolean withoutGoodsInPriceList) {
        return withoutGoodsInPriceList ? priceListRepository.findAllWithoutProductsInPriceList() : priceListRepository.findAll();
    }

    @Override
    public PriceList findPriceList(String idPriceList) {
        doCheckPriceListExist(UUID.randomUUID().toString(), idPriceList);
        return priceListRepository.findOne(idPriceList);
    }

    @Override
    public List<ProductsInPriceList> findGoodsListInPriceList(String idPriceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);

        return getSafeGoodsInPriceList.apply(priceListRepository.findOne(idPriceList));
    }

    @Override
    public ProductsInPriceList findGoodsInPriceList(String idPriceList, String idGoods) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        ProductsInPriceList goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceListRepository.findOne(idPriceList)).
                stream().filter(goodsInPriceList -> goodsInPriceList.getProducts().getId().equals(idGoods))
                .findFirst().orElse(null);

        if(goodsInPriceListAux == null){
            throw new ProductsInPriceListNotFoundException(ProductsInPriceListNotFoundException.DEFAULT_MESSAGE);
        }
        return goodsInPriceListAux;
    }

    @Override
    public PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        PriceList priceList = priceListRepository.findOne(idPriceList);

        Product goods  = productRepository.findOne(idGoods);
        List<ProductsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        ProductsInPriceList goodsInPriceList = new ProductsInPriceList();
        goodsInPriceList.setProducts(goods);
        goodsInPriceList.setPrice(price);

        int index = goodsInPriceListAux.indexOf(goodsInPriceList);

        if(index == -1){
            goodsInPriceListAux.add(goodsInPriceList);
        } else {
            goodsInPriceListAux.set(index, goodsInPriceList);
        }

        PriceList priceListAux = doSavePriceListData(correlationId, priceList);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.UPDATE);

        return priceListAux;
    }

    @Override
    public PriceList removeGoodsInPriceList(String idPriceList, String idGoods) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        PriceList priceList = priceListRepository.findOne(idPriceList);
        Product goods  = productRepository.findOne(idGoods);

        List<ProductsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        ProductsInPriceList goodsInPriceList = new ProductsInPriceList();
        goodsInPriceList.setProducts(goods);
        goodsInPriceListAux.remove(goodsInPriceList);

        PriceList priceListAux = doSavePriceListData(correlationId, priceList);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.UPDATE);

        return priceListAux;
    }

    @Override
    public PriceList updatePriceList(PriceList priceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, correlationId);
        return priceListRepository.save(priceList);
    }

    @Override
    public void deletePriceList(String idPriceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);

        priceListRepository.delete(idPriceList);
    }
}