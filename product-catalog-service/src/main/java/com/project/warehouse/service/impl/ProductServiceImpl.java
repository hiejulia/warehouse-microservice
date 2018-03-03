package com.project.warehouse.service.impl;



import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.model.PriceList;
import com.project.warehouse.model.Product;
import com.project.warehouse.service.AbstractService;
import com.project.warehouse.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ProductServiceImpl extends AbstractService implements ProductService {

    @Override
    public Product createProducts(Product goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);
        Product save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.CREATE);
        return save;
    }

    @Override
    public List<Product> findProductsList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findProductsList(String goodsCategory) {
        return productRepository.findByCategory(goodsCategory);
    }

    @Override
    public Product findProducts(String idGoods) {
        doCheckGoodsExist(UUID.randomUUID().toString(), idGoods);
        return productRepository.findOne(idGoods);
    }

    @Override
    public Product saveProductsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Product goods = productRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);
        stringStringMap.put(goodsAttributeKey, goodsAttributeValue);
        goods.setProductsAttribute(stringStringMap);

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Product removeProductsAttributeValue(String idGoods, String goodsAttributeKey) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Product goods = productRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);

        stringStringMap.remove(goodsAttributeKey);
        goods.setProductsAttribute(stringStringMap);

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Product updateProducts(Product goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);

        doCheckGoodsExist(correlationId, goods.getId());

        Product save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishGoodsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.UPDATE);
        return save;
    }

    @Override
    public void deleteProducts(String idGoods) {
        String correlationId = UUID.randomUUID().toString();

        doCheckGoodsExist(correlationId, idGoods);

        Product one = productRepository.findOne(idGoods);
        productRepository.delete(idGoods);

        eventDomainPubblishService.publishGoodsEvent(correlationId, idGoods, one.getName(),one.getBarCode(),
                one.getCategory(),EventTypeEnum.DELETE);
    }

//    @Override
//    protected Product doSaveGoodsData(String correlationId, Product goods) {
//        return super.doSaveGoodsData(correlationId, goods);
//    }
//
//    @Override
//    protected PriceList doSavePriceListData(String correlationId, PriceList priceList) {
//        return super.doSavePriceListData(correlationId, priceList);
//    }
//
//    @Override
//    protected void doCheckGoodsExist(String correlationId, String idGoods) {
//        super.doCheckGoodsExist(correlationId, idGoods);
//    }
//
//    @Override
//    protected void doCheckPriceListExist(String correlationId, String idPriceList) {
//        super.doCheckPriceListExist(correlationId, idPriceList);
//    }


}