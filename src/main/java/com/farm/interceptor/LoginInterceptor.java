package com.farm.interceptor;


import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 听风 on 2018/4/10.
 */

public class LoginInterceptor implements HandlerInterceptor {

    private static final List<String> excludeUri = new ArrayList<String>();
    static {
        excludeUri.add("/user/getCode");
        excludeUri.add("/user/createOpenId");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri = httpServletRequest.getRequestURI();
        if(excludeUri.contains(uri)){
            return true;
        }

        if(uri.startsWith("/admin")){
            return true;
        }

        HttpSession session = httpServletRequest.getSession();
        if(session == null || StringUtils.isEmpty(session.getAttribute("openId"))){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/index.html");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
