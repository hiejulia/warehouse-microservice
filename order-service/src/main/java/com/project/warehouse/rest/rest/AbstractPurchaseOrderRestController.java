package com.project.warehouse.rest.rest;



import com.project.warehouse.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractPurchaseOrderRestController {

    // autowired : PurchaseOrderServive, SecurityUtils

    @Autowired
    protected PurchaseOrderService purchaseOrderService;

    @Autowired
    protected SecurityUtils securityUtils;
}