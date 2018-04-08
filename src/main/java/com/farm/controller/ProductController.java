package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.ProductInfoVO;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/product/addOrUpdateProduct", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult addProduct(ProductInfoVO productInfoVO){
        return productService.addOrUpdateProduct(productInfoVO);
    }

    @RequestMapping(value = "/product/productList", method = RequestMethod.GET)
    @ResponseBody
    public List<ProductInfoVO> productList(){
        return productService.productList();
    }


}
