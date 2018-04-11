package com.farm.service;

import com.farm.dto.AuthTokenVO;
import com.farm.dto.UserInfoVO;
import com.farm.mapper.UserMapper;
import com.farm.util.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Value("${weixin.appid}")
    private String appid;

    @Value("${weixin.appsecret}")
    private String appsecret;

    public void addUser(UserInfoVO userInfo){
        UserInfoVO userInfoVO = userMapper.findUserByOpenId(userInfo);
        if(userInfoVO == null){
            userMapper.saveUserInfo(userInfo);
        }

    }

    public String createOpenId(String code) {

        String tokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret + "&code=" + code + "&grant_type=authorization_code";
        AuthTokenVO authTokenVO = NetUtil.httpGet(tokenUrl, AuthTokenVO.class);
        String openid = authTokenVO.getOpenid();
        if(StringUtils.isEmpty(openid)){
            return null;
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        userInfoVO.setOpenId(openid);
        addUser(userInfoVO);
        return openid;
    }
}
