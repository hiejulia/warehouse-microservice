package com.project.warehouse.service.impl;



import com.project.warehouse.event.model.EventTypeEnum;
import com.project.warehouse.model.Product;
import com.project.warehouse.repository.ProductRepository;
import com.project.warehouse.service.AbstractService;
import com.project.warehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;


@Service
public class ProductServiceImpl extends AbstractService implements ProductService {


    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createGoods(Product goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);
        Product save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishProductsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.CREATE);
        return save;
    }

    @Override
    public List<Product> findGoodsList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findGoodsList(String goodsCategory) {
        return productRepository.findByCategory(goodsCategory);
    }

    @Override
    public Product findGoods(String idGoods) {
        doCheckGoodsExist(UUID.randomUUID().toString(), idGoods);
        return productRepository.findOne(idGoods);
    }

    @Override
    public Product saveGoodsAttributeValue(String idGoods, String goodsAttributeKey, String goodsAttributeValue) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Product goods = productRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);
        stringStringMap.put(goodsAttributeKey, goodsAttributeValue);
        goods.setProductsAttribute(stringStringMap);

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Product removeGoodsAttributeValue(String idGoods, String goodsAttributeKey) {
        String correlationId = UUID.randomUUID().toString();
        doCheckGoodsExist(correlationId, idGoods);

        Product goods = productRepository.findOne(idGoods);
        Map<String, String> stringStringMap = getSafeGoodsAttribute.apply(goods);

        stringStringMap.remove(goodsAttributeKey);
        goods.setProductsAttribute(stringStringMap);

        return doSaveGoodsData(correlationId, goods);
    }

    @Override
    public Product updateGoods(Product goods) {
        String correlationId = UUID.randomUUID().toString();
        priceListDataValidator.validate(correlationId, goods);

        doCheckGoodsExist(correlationId, goods.getId());

        Product save = doSaveGoodsData(correlationId, goods);

        eventDomainPubblishService.publishProductsEvent(correlationId,goods.getId(), goods.getName(),
                goods.getBarCode(), goods.getCategory(), EventTypeEnum.UPDATE);
        return save;
    }

    @Override
    public void deleteGoods(String idGoods) {
        String correlationId = UUID.randomUUID().toString();

        doCheckGoodsExist(correlationId, idGoods);

        Product one = productRepository.findOne(idGoods);
        productRepository.delete(idGoods);

        eventDomainPubblishService.publishProductsEvent(correlationId, idGoods, one.getName(),one.getBarCode(),
                one.getCategory(), EventTypeEnum.DELETE);
    }
}