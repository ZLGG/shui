package com.gs.lshly.biz.support.user.service.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Project: boss-sdk
 * @Package: com.lakala.boss.api.utils
 * @Description: HTTP工具类
 * @author: LXF
 * @date Date: 2019年10月23日 18:00
 * @version: V1.0.0
 */
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * post请求
     *
     * @param url
     * @param jsonStrParams
     * @return
     */
    public static String doPost(String url, String jsonStrParams) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader(new BasicHeader("Accept", "application/json"));
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
        StringEntity entity = new StringEntity(jsonStrParams, "UTF-8");
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();

            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity, "UTF-8");
                return jsonString;
            } else {
                System.out.println("HttpStatus=" + state);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * post请求
     *
     * @param url
     * @param jsonStrParams
     * @return
     */
    public static String doPost(String url, String jsonStrParams, String charset) throws Exception {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);// 创建httpPost
        httpPost.setHeader(new BasicHeader("Accept", "application/json"));
        httpPost.setHeader(new BasicHeader("Content-Type", "application/json"));
        StringEntity entity = new StringEntity(jsonStrParams, charset);
        httpPost.setEntity(entity);
        CloseableHttpResponse response = null;

        try {

            response = httpclient.execute(httpPost);
            StatusLine status = response.getStatusLine();
            int state = status.getStatusCode();

            if (state == HttpStatus.SC_OK) {
                HttpEntity responseEntity = response.getEntity();
                String jsonString = EntityUtils.toString(responseEntity, charset);
                return jsonString;
            } else {
                System.out.println("HttpStatus=" + state);
            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}