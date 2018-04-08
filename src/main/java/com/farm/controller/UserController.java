package com.farm.controller;

import com.farm.service.UserService;
import com.farm.util.JsonUtil;
import com.farm.util.NetUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController extends BaseController{

    @Value("${weixin.appid}")
    private String appid;

    @Autowired
    UserService userService;

    @RequestMapping("/user/getCode")
    @ResponseBody
    public void ReGuserServiceetOpenId(HttpServletRequest request, HttpServletResponse response){
        String openId = (String)request.getSession().getAttribute("openId");
        if(openId != null){
            try {
                response.sendRedirect(request.getContextPath() + "/index.html");
            }catch (Exception e){
                e.printStackTrace();
            }
            return;
        }
        String redirect_uri = "/user/login";
        String codeUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
                + appid + "&redirect_uri=" + redirect_uri
                + "?action=viewtest&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
        NetUtil.httpGet(codeUrl);
    }

    @RequestMapping("/user/createOpenId")
    @ResponseBody
    public void createOpenId(HttpServletRequest request, HttpServletResponse response){
        logger.info("createOpenId req:" + JsonUtil.getJsonString(request));
        String code = request.getParameter("code");
        String openId = userService.createOpenId(code);
        if(StringUtils.isEmpty(openId)){
            logger.error("获取openId失败");
            try {
                response.sendRedirect(request.getContextPath() + "/error.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        request.getSession().setAttribute("openId", openId);
        try {
            response.sendRedirect(request.getContextPath() + "/index.html");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
