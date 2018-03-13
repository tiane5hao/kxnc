package com.farm.util;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class NetUtil {

    public static Logger log = org.slf4j.LoggerFactory.getLogger(NetUtil.class);

    private static final HttpConnectionManager httpConnectionManager;

    public static final int CONN_TIMEOUT = 	30000;
    public static final int SOCK_TIMEOUT = 	30000;
    public static final int MAX_CONN = 50;
    public static final int MAX_CONN_PRE_HOST = 20;
    static {
        httpConnectionManager = new MultiThreadedHttpConnectionManager();
        HttpConnectionManagerParams params = httpConnectionManager.getParams();
        params.setConnectionTimeout(CONN_TIMEOUT);
        params.setSoTimeout(SOCK_TIMEOUT);
        params.setDefaultMaxConnectionsPerHost(MAX_CONN_PRE_HOST);
        params.setMaxTotalConnections(MAX_CONN);
    }


    /**
     * HttpClient 调用socket接口
     * @param url  服务地址
     * @param o  请求参数 对象
     * @return
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonGenerationException
     */
    public static <T> T httpPost(String url , Object o, Class<T> clazz )  {

        log.info("推送报文url====" +url);

        String resultJsonString = "";
        String  params = JsonUtil.getJsonString(o);
        // 发送报文
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
        PostMethod postMethod = new PostMethod(url);
        postMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+"UTF-8");
        try {
            log.info("推送报文参数，params====" + params);
            RequestEntity requestEntity = new StringRequestEntity(params,"text/xml","UTF-8");
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            resultJsonString = postMethod.getResponseBodyAsString();
            log.info("推送返回报文:" + resultJsonString);
            if(StringUtils.isEmpty(resultJsonString)){
                return null;
            }
            return JsonUtil.stringToObject(resultJsonString, clazz);
        } catch (HttpException e) {
            log.error("客户端异常:" + e.getMessage());
        } catch (IOException e) {
            log.error("客户端异常:" + e.getMessage());
        } finally {
            postMethod.releaseConnection();
        }

        return null;

    }

    public static  <T> T httpGet(String url ,  Class<T> clazz ){

        String resultJsonString = "";
        // 发送报文
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+"UTF-8");
        try {
            log.info("推送报文url====" +url);

            httpClient.executeMethod(getMethod);
            resultJsonString = getMethod.getResponseBodyAsString();
            log.info("推送返回报文:" + resultJsonString);
            if(StringUtils.isEmpty(resultJsonString)){
                return null;
            }
            return JsonUtil.stringToObject(resultJsonString, clazz);
        } catch (HttpException e) {
            log.error("客户端异常:" + e.getMessage());
        } catch (IOException e) {
            log.error("客户端异常:" + e.getMessage());
        } finally {
            getMethod.releaseConnection();
        }

        return null;
    }

    public static  String httpGet(String url){

        String resultJsonString = "";
        // 发送报文
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(10000);
        httpClient.getHttpConnectionManager().getParams().setSoTimeout(10000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset="+"UTF-8");
        try {
            log.info("推送报文url====" +url);

            httpClient.executeMethod(getMethod);
            resultJsonString = getMethod.getResponseBodyAsString();
            log.info("推送返回报文:" + resultJsonString);

        } catch (HttpException e) {
            log.error("客户端异常:" + e.getMessage());
        } catch (IOException e) {
            log.error("客户端异常:" + e.getMessage());
        } finally {
            getMethod.releaseConnection();
        }
        return resultJsonString;
    }
}
