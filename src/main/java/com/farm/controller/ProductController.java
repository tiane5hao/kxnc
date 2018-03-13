package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.ProductInfoVO;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping("/product/addOrUpdateProduct")
    public MessageResult addProduct(ProductInfoVO productInfoVO){
        return productService.addOrUpdateProduct(productInfoVO);
    }

    @RequestMapping("/product/productList")
    public List<ProductInfoVO> productList(ProductInfoVO productInfoVO){
        return productService.productList(productInfoVO);
    }


}
