package com.gs.lshly.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import lombok.extern.slf4j.Slf4j;

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
	private final static Integer SOCKET_TIMEOUT = 10000;
	private final static Integer CONNECT_TIMEOUT = 10000;
	
	static RequestConfig newRequestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
    	
	private HttpsUtil(){}

	/**
	 * post请求
	 * @param url
	 * @param jsonStrParams
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonStrParams) throws Exception {

		return doPost(url, jsonStrParams, "UTF-8");
	}

	/**
	 * post请求
	 * @param url
	 * @param jsonStrParams
	 * @return
	 * @throws Exception
	 */
	public static String doPost(String url, String jsonStrParams, String charset) throws Exception {
		HttpClient httpClient = null;
		HttpPost httpPost = null;
		String result = null;
		HttpResponse response = null;

		httpClient = new SSLClient();
		httpPost = new HttpPost(url);
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

		return result;
	}

	 /**
      * 向指定 URL 发送POST方法的请求
      * @param url 发送请求的 URL
      * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
      * @return 所代表远程资源的响应结果
      */
		 public static String sendPost(String url, String param) {

			 PrintWriter out = null;
			 BufferedReader in = null;
			 String result = "";
			try {

				// 创建SSLContext对象，并使用我们指定的信任管理器初始化     
				 TrustManager[] tm = { new MyX509TrustManager() };
				 SSLContext sslContext = SSLContext.getInstance("SSL");
				 sslContext.init(null, tm, new java.security.SecureRandom());

				 // 从上述SSLContext对象中得到SSLSocketFactory对象     
				 SSLSocketFactory ssf = sslContext.getSocketFactory();

				 // 打开和URL之间的连接
				URL realUrl = new URL(url);
				 HttpsURLConnection conn = (HttpsURLConnection) realUrl.openConnection();
				 conn.setSSLSocketFactory(ssf);

				 // 设置通用的请求属性
				 conn.setRequestProperty("accept", "*/*");
				 conn.setRequestProperty("connection", "Keep-Alive");
				 conn.setRequestProperty("content-Type", "application/json");
				 conn.setRequestProperty("user-agent",
									"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				// 发送POST请求必须设置如下两行
				 conn.setDoOutput(true);
				 conn.setDoInput(true);
				 // 获取URLConnection对象对应的输出流
				 out = new PrintWriter(conn.getOutputStream());
				// 发送请求参数
				 out.print(param);
				// flush输出流的缓冲
				out.flush();
				 // 定义BufferedReader输入流来读取URL的响应
				 in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				 String line;
				 while ((line = in.readLine()) != null) {
					result += line;
				 }
				 System.out.println("-----result-----"+result);
			 } catch (Exception e) {
				 System.out.println("发送 POST 请求出现异常！"+e);
				 e.printStackTrace();
			 }
			//使用finally块来关闭输出流、输入流
			 finally{
				 try{
					if(out!=null){
						out.close();
					}
					 if(in!=null){
						in.close();
					}
				}
				catch(IOException ex){
					ex.printStackTrace();
				}
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
		
		
	    /**
	     * 没有请求参数的GET请求
	     *
	     * @param url
	     * @return
	     * @throws Exception
	     */
	    public static String get(String url) throws Exception {
	        return get(url, null, null);
	    }
	    
	    /**
	     * 包含请求头和请求参数的GET请求
	     *
	     * @param url
	     * @param params
	     * @param headers
	     * @return
	     * @throws Exception
	     */
	    public static String get(String url, Map<String, String> params, Map<String, String> headers) throws Exception {
	        //拼接请求URL
	        String newUrl = appedParamsToUrl(url, params);
	        log.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "GET", url, headers, params);
	        //创建请求客户端
	        CloseableHttpClient httpClient = HttpClients.createDefault();
	        //构造GET请求链接
	        HttpGet httpGet = new HttpGet(newUrl);
	        httpGet.setConfig(newRequestConfig);
	        //创建响应客户端
	        CloseableHttpResponse response = null;
	        //设置请求头
	        if (null != headers) {
	            for (String header : headers.keySet()) {
	                String value = headers.get(header);
	                httpGet.setHeader(header, value);
	            }
	        }
	        String content = "";
	        try {
	            response = httpClient.execute(httpGet);
	            Integer statusCode = response.getStatusLine().getStatusCode();
	            if (statusCode == 200) {
	                //获取请求体内容
	                content = EntityUtils.toString(response.getEntity(), "UTF-8");
	            }
	        } finally {
	            if (response != null) {
	                response.close();
	            }
	            httpClient.close();
	        }
	        return content;
	    }
	    public static String appedParamsToUrl(String url, Map<String, String> params) throws Exception {
	        String body = "";
	        if (params != null) {
	            boolean first = true;
	            for (String param : params.keySet()) {
	                if (first) {
	                    first = false;
	                } else {
	                    body += "&";
	                }
	                String value = params.get(param);
	                body += URLEncoder.encode(param, "UTF-8") + "=";
	                body += URLEncoder.encode(value, "UTF-8");
	            }
	            return url + "?" + body;
	        } else
	            return url;
	    }
}