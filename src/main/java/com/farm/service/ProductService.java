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
import java.util.Map;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductMapper mapper;

    public MessageResult addOrUpdateProduct(ProductInfoVO productInfoVO){
        MessageResult messageResult = new MessageResult();
        try {
            if(StringUtils.isEmpty(productInfoVO.getProductId())){
                String productId = UUID.randomUUID().toString().replace("-","");
                productInfoVO.setProductId(productId);
                mapper.addProduct(productInfoVO);
            }else {

                mapper.updateProduct(productInfoVO);
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

    public int productCount(Map<String, String> param) {
       return mapper.productCount(param);
    }
}
