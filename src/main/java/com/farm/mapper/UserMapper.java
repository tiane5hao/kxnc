package com.farm.mapper;

import com.farm.dto.UserInfoVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface UserMapper {

    @Insert("INSERT INTO `farm`.`user` ( `open_id`, `user_name`, `user_mobile`, `create_time`) " +
            "VALUES ( #{openId}, #{userName}, #{userMobile}, now())")
    void saveUserInfo(UserInfoVO userInfo);
}
