package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ProductController extends BaseController{

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/admin/product/addOrUpdateProduct", method = RequestMethod.POST)
    public void addOrUpdateProduct(ProductInfoVO productInfoVO, HttpServletRequest req, HttpServletResponse resp){
        productService.addOrUpdateProduct(productInfoVO);
        try {
            req.getRequestDispatcher("/html/productList.html").forward(req,resp);
        }catch (Exception e){

        }
    }

    @RequestMapping(value = "/product/productList")
    @ResponseBody
    public List<ProductInfoVO> productList(Page page){
        return productService.productList(page);
    }

    @RequestMapping(value = "/admin/product/productList")
    @ResponseBody
    public Map<String, Object> adminProductList(@RequestParam("page") Integer page,
                                                @RequestParam("rows") Integer rows){
        Page page1 = new Page();
        page1.setPage(page);
        page1.setRows(rows);
        List<ProductInfoVO> list = productService.productList(page1);
        int count = productService.productCount(null);
        Map<String, Object> map = new HashMap<>(2);
        map.put("rows", list);
        map.put("total", count);
        return map;
    }

}
