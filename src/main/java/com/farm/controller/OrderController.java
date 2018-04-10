package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.ProductInfoVO;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/order/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult addOrder(ProductInfoVO productInfoVO){
        return productService.addOrUpdateProduct(productInfoVO);
    }

    @RequestMapping(value = "/order/orderList", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInfoVO> orderList(){
        return productService.productList();
    }


}
