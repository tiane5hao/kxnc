package com.farm.controller;

import com.farm.common.MessageResult;
import com.farm.dto.OrderInfo;
import com.farm.dto.OrderQueryParam;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import com.farm.service.OrderService;
import com.farm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class OrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/order/addOrder", method = RequestMethod.POST)
    @ResponseBody
    public MessageResult addOrder(OrderInfo orderInfo, HttpServletRequest request){
        String openId = getOpenId(request);
        MessageResult messageResult = new MessageResult();
        try {
            orderInfo.setOpenId(openId);
            orderService.addOrder(orderInfo);
            return messageResult;
        }catch (Exception e){
            logger.error("addOrder error", e);
            messageResult.setError("新增订单失败");
            return messageResult;
        }

    }

    @RequestMapping(value = "/admin/order/updateOrderStatus", method = RequestMethod.GET)
    @ResponseBody
    public MessageResult updateOrderStatus(OrderInfo orderInfo){
        MessageResult messageResult = new MessageResult();
        try {
            orderService.updateOrderStatus(orderInfo);
            return messageResult;
        }catch (Exception e){
            logger.error("updateOrderStatus error", e);
            messageResult.setError("修改订单状态失败");
            return messageResult;
        }
    }

    @RequestMapping(value = "/order/findOrderList", method = RequestMethod.GET)
    @ResponseBody
    public MessageResult findOrderList(HttpServletRequest request, Page page){

        MessageResult messageResult = new MessageResult();
        try {
            String openId = getOpenId(request);
            List<OrderInfo> list = orderService.findOrderList(page, openId);
            messageResult.setData(list);
            return messageResult;
        }catch (Exception e){
            logger.error("findOrderList error", e);
            messageResult.setError("查询订单列表异常");
            return messageResult;
        }
    }

    @RequestMapping(value = "/admin/order/findOrderList", method = RequestMethod.GET)
    @ResponseBody
    public MessageResult findOrderListByAdmin(HttpServletRequest request, Page page, OrderQueryParam param){

        MessageResult messageResult = new MessageResult();
        try {
            List<OrderInfo> list = orderService.findOrderList(page, param);
            messageResult.setData(list);
            return messageResult;
        }catch (Exception e){
            logger.error("findOrderListByAdmin error", e);
            messageResult.setError("查询订单列表异常");
            return messageResult;
        }
    }


}
