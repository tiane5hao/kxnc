package com.farm.mapper;

import com.farm.dto.Page;
import com.farm.dto.ProductInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductMapper {

    @Insert("INSERT INTO `product` ( `product_id`, `product_name`, `price`, `image`, `classify_id`) " +
            "VALUES (#{productId}, #{productName}, #{price}, #{image}, #{classifyId});")
    void addProduct(ProductInfoVO productInfoVO);

    @Select("select price from product where product_id = #{productId}")
    double queryProductPrice(ProductInfoVO productInfoVO);

    @Select("select p.product_id as productId, product_name as productName, price, number" +
            "from product p , order_product op where p.product_id = op.product_id" +
            "and op.order_id = #{orderId}")
    List<ProductInfoVO> findProductListByOrderId(String orderId);

    @Select("select product_id as productId,product_name as productName,status," +
            " price, image,classify_id as classifyId from product limit #{start},#{rows}")
    List<ProductInfoVO> productList(Page page);
}
