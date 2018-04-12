package com.farm.service;

import com.farm.dto.OrderInfo;
import com.farm.dto.OrderQueryParam;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import com.farm.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    ProductService productService;

    @Transactional
    public void addOrder(OrderInfo orderInfo){
        double price = productService.calculateOrderPrice(orderInfo.getList());
        orderInfo.setPrice(price);
        String orderId = UUID.randomUUID().toString().replace("-", "");
        orderInfo.setOrderId(orderId);
        orderMapper.addOrder(orderInfo);
        for(ProductInfoVO productInfoVO : orderInfo.getList()){
            productInfoVO.setOrderId(orderId);
            orderMapper.addOrderProduct(productInfoVO);
        }
    }

    public void updateOrderStatus(OrderInfo orderInfo){
        orderMapper.updateOrderStatus(orderInfo);
    }

    public List<OrderInfo> findOrderList(Page page, String openId) {
        List<OrderInfo> orderList = orderMapper.findOrderList(page, openId);
        for(OrderInfo orderInfo : orderList){
            List<ProductInfoVO> productInfoVOList = productService.productListByOrderId(orderInfo.getOrderId());
            orderInfo.setList(productInfoVOList);
        }
        return orderList;
    }


    public List<OrderInfo> findOrderListByAdmin(Page page, OrderQueryParam param) {
        List<OrderInfo> orderList = orderMapper.findOrderListByAdmin(page, param);
        for(OrderInfo orderInfo : orderList){
            List<ProductInfoVO> productInfoVOList = productService.productListByOrderId(orderInfo.getOrderId());
            orderInfo.setList(productInfoVOList);
        }
        return orderList;
    }
}
