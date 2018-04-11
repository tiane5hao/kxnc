package com.farm.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    public Logger logger = LoggerFactory.getLogger(this.getClass());

    public String getOpenId(HttpServletRequest request){
        return request.getSession().getAttribute("openId").toString();
    }
}
