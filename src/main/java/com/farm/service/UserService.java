package com.farm.service;

import com.farm.dto.UserInfoVO;
import com.farm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void addUser(UserInfoVO userInfo){
        userMapper.saveUserInfo(userInfo);
    }
}
