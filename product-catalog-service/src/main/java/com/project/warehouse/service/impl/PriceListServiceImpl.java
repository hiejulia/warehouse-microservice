package com.project.warehouse.service.impl;



import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.service.AbstractService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PriceListServiceImpl extends AbstractService {

    @Override
    public PriceList createPriceList(PriceList priceList) {
        String correlationId = UUID.randomUUID().toString();
        priceList.setProductsInPriceList(Optional.ofNullable(priceList.getGoodsInPriceList()).orElse(new ArrayList<>()));
        priceListDataValidator.validate(correlationId, priceList);
        Price save = doSavePriceListData(correlationId, priceList);

        eventDomainPubblishService.publishPriceListEvent(correlationId,priceList.getId(),
                priceList.getName(), EventTypeEnum.CREATE);
        return save;
    }

    @Override
    public List<PriceList> findPriceLists(boolean withoutGoodsInPriceList) {
        return withoutGoodsInPriceList ? priceListRepository.findAllWithoutGoodsInPriceList() : priceListRepository.findAll();
    }

    @Override
    public PriceList findPriceList(String idPriceList) {
        doCheckPriceListExist(UUID.randomUUID().toString(), idPriceList);
        return priceListRepository.findOne(idPriceList);
    }

    @Override
    public List<GoodsInPriceList> findGoodsListInPriceList(String idPriceList) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);

        return getSafeGoodsInPriceList.apply(priceListRepository.findOne(idPriceList));
    }

    @Override
    public GoodsInPriceList findGoodsInPriceList(String idPriceList, String idGoods) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        GoodsInPriceList goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceListRepository.findOne(idPriceList)).
                stream().filter(goodsInPriceList -> goodsInPriceList.getGoods().getId().equals(idGoods))
                .findFirst().orElse(null);

        if(goodsInPriceListAux == null){
            throw new GoodsInPriceListNotFoundException(GoodsInPriceListNotFoundException.DEFAULT_MESSAGE);
        }
        return goodsInPriceListAux;
    }

    @Override
    public PriceList saveGoodsInPriceList(String idPriceList, String idGoods, BigDecimal price) {
        String correlationId = UUID.randomUUID().toString();
        doCheckPriceListExist(correlationId, idPriceList);
        doCheckGoodsExist(correlationId, idGoods);

        PriceList priceList = priceListRepository.findOne(idPriceList);

        Product goods  = goodsRepository.findOne(idGoods);
        List<GoodsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
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
        Product goods  = goodsRepository.findOne(idGoods);

        List<GoodsInPriceList> goodsInPriceListAux = getSafeGoodsInPriceList.apply(priceList);

        GoodsInPriceList goodsInPriceList = new GoodsInPriceList();
        goodsInPriceList.setGoods(goods);
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