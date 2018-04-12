package com.farm.mapper;

import com.farm.dto.OrderInfo;
import com.farm.dto.OrderQueryParam;
import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

   @Insert("INSERT INTO `order` ( open_id,`order_id`, `price`, `addressee`, `phone`, `status`, `create_time`)" +
           " VALUES (#{openId}, #{orderId}, #{price}, #{addressee}, #{phone}, 1, now());")
    void addOrder(OrderInfo orderInfo);

    @Insert("INSERT INTO `order_product` (`order_id`, `product_id`, `number`, `create_time`)" +
            "VALUES (#{orderId}, #{productId}, #{number}, now());")
    void addOrderProduct(ProductInfoVO productInfoVO);

    @Update("update order set status = #{status} where order_id = #{orderId}")
    void updateOrderStatus(OrderInfo orderInfo);

    @Select("select order_id as orderId, price,addressee, phone, status, create_time as createTime" +
            "from order where open_id = #{openId} limit #{page.start}, #{page.pageSize}")
    List<OrderInfo> findOrderList(@Param("page") Page page, @Param("openId") String openId);

    @Select("select order_id as orderId, price,addressee, phone, status, create_time as createTime" +
            "from order where status=#{param.status} limit #{page.start}, #{page.pageSize}")
    List<OrderInfo> findOrderListByAdmin(@Param("page") Page page, @Param("param")OrderQueryParam param);

}
