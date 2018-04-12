package com.farm.service;

import com.farm.common.MessageResult;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import com.farm.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductMapper mapper;

    public MessageResult addOrUpdateProduct(ProductInfoVO productInfoVO){
        MessageResult messageResult = new MessageResult();
        try {
            if(StringUtils.isEmpty(productInfoVO.getProductId())){
//                mapper.addProduct(productInfoVO);
            }else {
//                mapper.update
            }
        }catch (Exception e){
            messageResult.setError();
        }
        return messageResult;
    }

    public List<ProductInfoVO> productList(Page page) {
        page.initStart();
        return mapper.productList(page);
    }

    public double calculateOrderPrice(List<ProductInfoVO> list){
        double allPrice = 0.0;
        for(ProductInfoVO productInfoVO : list){
            double price = mapper.queryProductPrice(productInfoVO);
            allPrice += price * productInfoVO.getNumber();
        }
        return allPrice;
    }

    public List<ProductInfoVO> productListByOrderId(String orderId) {
        return mapper.findProductListByOrderId(orderId);
    }
}
