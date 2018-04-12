package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/admin/product/addOrUpdateProduct", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult addOrUpdateProduct(ProductInfoVO productInfoVO){
        return productService.addOrUpdateProduct(productInfoVO);
    }

    @RequestMapping(value = "/product/productList")
    @ResponseBody
    public List<ProductInfoVO> productList(Page page){
        return productService.productList(page);
    }

    @RequestMapping(value = "/admin/product/productList")
    @ResponseBody
    public List<ProductInfoVO> adminProductList(@RequestParam("page") Integer page,
                                                @RequestParam("rows") Integer rows){
        Page page1 = new Page();
        page1.setPage(page);
        page1.setRows(rows);
        return productService.productList(page1);
    }

}
