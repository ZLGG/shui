package com.gs.lshly.common.utils;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

/**
 * @Project: boss-sdk
 * @Package: com.lakala.boss.api.utils
 * @Description: HTTPS工具类
 * @author: LXF
 * @date Date: 2019年10月23日 18:00
 * @version: V1.0.0
 */
@Slf4j
public class HttpsUtil {

	private HttpsUtil(){}

	/**
	 * post请求
	 *
	 * @param url
	 * @param jsonStrParams
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonStrParams) {

		return doPost(url, jsonStrParams, "UTF-8");
	}

	/**
	 * post请求
	 *
	 * @param url
	 * @param jsonStrParams
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonStrParams, String charset) {

		String result = null;
		try {
			HttpClient httpClient = new SSLClient();
			;
			HttpPost httpPost = new HttpPost(url);
			HttpResponse response = null;
			charset = StrUtil.isBlank(charset) ? "UTF-8" : charset;
			httpPost.addHeader("Content-Type", "application/json");
			StringEntity se = new StringEntity(jsonStrParams, charset);
			se.setContentType("text/json");
			se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
			httpPost.setEntity(se);
			response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			log.info("请求错误");
		}
		return result;
	}
	/**
	 * get请求
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url) {
		return doGet(url, "UTF-8");
	}
	/**
	 * get请求
	 * @param url
	 * @param charset
	 * @return
	 * @throws Exception
	 */
	public static String doGet(String url, String charset) {
		String result = null;
		try {
			HttpClient httpClient = new SSLClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse response = null;
			charset = StrUtil.isBlank(charset) ? "UTF-8" : charset;
			response = httpClient.execute(httpGet);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					result = EntityUtils.toString(resEntity, charset);
				}
			}
		} catch (Exception e) {
			log.info("请求错误");
		}
		return result;
	}

	private static Integer callReadTimeOut = 10;
	private static Integer callConnectTimeOut = 5;
	private static Integer callMaxTotal = 5000;
	private static Integer callMaxPerRoute = 500;
	volatile static HttpClient client;

	public static HttpClient getClient() {
		RequestConfig.Builder requestBuilder = RequestConfig.custom();
		requestBuilder.setConnectTimeout(callConnectTimeOut * 1000);
		requestBuilder.setSocketTimeout(callReadTimeOut * 1000);

		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(callMaxTotal);
		cm.setDefaultMaxPerRoute(callMaxPerRoute);
		client = HttpClientBuilder.create()
				.setDefaultRequestConfig(requestBuilder.build())
				.setConnectionManager(cm)
				.build();
		return client;
	}

}