package com.farm.service;

import com.farm.common.MessageResult;
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

    public List<ProductInfoVO> productList() {
        return new ArrayList<>();
    }
}
