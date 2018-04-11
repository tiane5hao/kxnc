package com.farm.mapper;

import com.farm.dto.UserInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO `user` ( `open_id`, `user_name`, `user_mobile`, `create_time`) " +
            "VALUES ( #{openId}, #{userName}, #{userMobile}, now())")
    void saveUserInfo(UserInfoVO userInfo);

    @Select("select open_id as openId, create_time createTime from user where open_id = #{openId}")
    UserInfoVO findUserByOpenId(UserInfoVO userInfo);
}
