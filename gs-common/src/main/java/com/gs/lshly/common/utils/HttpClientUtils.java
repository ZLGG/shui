package com.gs.lshly.common.utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * 
 *
 * 
 * @author yingjun
 * @date 2020年6月22日 下午2:59:05
 */
@SuppressWarnings("deprecation")
public class HttpClientUtils {
    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
    private final static Integer CONNECT_TIMEOUT = 10000;
    private final static Integer SOCKET_TIMEOUT = 10000;
    static RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).setConnectionRequestTimeout(SOCKET_TIMEOUT).build();
    static RequestConfig newRequestConfig = RequestConfig.custom().setConnectTimeout(CONNECT_TIMEOUT).setSocketTimeout(SOCKET_TIMEOUT).build();
    private Map<String, String> headers;
    private String host;
    private String port;
    private String protocol;


    public HttpClientUtils(Map<String, String> headers, String protocol, String host, String port) {
        this.headers = headers;
        this.host = host + ":";
        this.port = port + "/";
        this.protocol = protocol + "://";
    }

    public HttpClientUtils() {
    }

    /**
     * httpClient发送get请求
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String sendHttpGet(String url, Map<String, String> params) throws Exception {
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
        List<NameValuePair> listPair = new ArrayList<>();
        Set<String> keys = params.keySet();
        for (String key : keys) {
            listPair.add(new BasicNameValuePair(key, params.get(key)));
        }
        String str = EntityUtils.toString(new UrlEncodedFormEntity(listPair, "utf-8"));
        HttpGet httpGet;
        if (StringUtils.isEmpty(str)) {
            httpGet = new HttpGet(url);
        } else {
            httpGet = new HttpGet(url + "?" + str);
        }
        CloseableHttpResponse httpResponse = httpclient.execute(httpGet);
        return EntityUtils.toString(httpResponse.getEntity(), "utf-8");
    }

    public String sendPost(String url, Map<String, String> param) throws Exception {
        String stringParam = "";
        if (param != null) {
            for (Map.Entry<String, String> e : param.entrySet()) {
                stringParam += (e.getKey() + "=" + e.getValue() + "&");
            }
            stringParam = stringParam.substring(0, stringParam.length() - 1);
        }


        PrintWriter out = null;
        BufferedReader in = null;
        OutputStreamWriter outputStreamWriter = null;
        String result = "";
        try {
            URL realUrl = new URL(this.protocol + this.host + this.port + url);
            HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "UTF-8");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Accept-Language", Locale.getDefault().toString());
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            writeHeaders(conn);
            conn.setDoOutput(true);//发送post请求时使用


            outputStreamWriter = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            outputStreamWriter.write(stringParam);

            // out.print(param);
            outputStreamWriter.flush();
            // out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
            System.out.println("result-------------------" + result);
            return result;
        } catch (Exception e) {
            // e.printStackTrace();
            throw e;
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void writeHeaders(URLConnection connection) {
        for (Map.Entry<String, String> e : headers.entrySet()) {
            connection.setRequestProperty(e.getKey(), e.getValue());
        }
    }

/******************************************新增的方法**********************************************/

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
     * 带有请求参数的GET
     *
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String get(String url, Map<String, String> params) throws Exception {
        return get(url, params, null);
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
        logger.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "GET", url, headers, params);
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

    /**
     * 无请求参数POST 请求
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String postForm(String url) throws IOException {
        return postForm(url, null, null);
    }

    /**
     * 带参数的post form
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        return postForm(url, params, null);
    }

    /**
     * 带参数和请求头的post form
     *
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(newRequestConfig);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0" +
                ".2924.87 Safari/537.36");
        logger.info("请求方式:{},请求地址:{},请求头:{},请求参数:{}", "POST", url, headers, params);
        CloseableHttpResponse response = null;
        String content = "";
        //设置参数 postform 表单参数
        if (null != params) {
            List<NameValuePair> parameters = new ArrayList<NameValuePair>(0);
            for (String param : params.keySet()) {
                String value = params.get(param);
                parameters.add(new BasicNameValuePair(param, value));
            }
            //构造form表单的实体
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(parameters, "utf-8");
            httpPost.setEntity(formEntity);
        }
        if (null != headers) {
            for (String param : headers.keySet()) {
                String value = headers.get(param);
                httpPost.setHeader(param, value);
            }
        }
        try {
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
            httpclient.close();
        }
        logger.info("请求回调结果：{}",content);
        return content;
    }

    public static String postBody(String url, Map<String, String> params) throws IOException {
        return postBody(url, params, null);
    }

    /***
     * 带参数和请求头的post body
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postBody(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        logger.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "POST", url, headers, params);
        httpPost.setConfig(newRequestConfig);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        String charSet = "UTF-8";
        String jsonString = JSONObject.toJSONString(params);
        StringEntity entity = new StringEntity(jsonString, charSet);
        httpPost.setEntity(entity);
        if (null != headers) {
            for (String header : headers.keySet()) {
                String value = headers.get(header);
                httpPost.setHeader(header, value);
            }
        }
        CloseableHttpResponse response = null;
        String content = "";
        try {
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
            httpclient.close();
        }
        return content;
    }

    /***
     * 带参数和请求头的post body
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postBodyString(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(newRequestConfig);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        logger.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "POST", url, headers, params);
        String charSet = "UTF-8";
        String jsonString = JSONObject.toJSONString(params);
        StringEntity entity = new StringEntity(jsonString, charSet);
        httpPost.setEntity(entity);
        if (null != headers) {
            for (String header : headers.keySet()) {
                String value = headers.get(header);
                httpPost.setHeader(header, value);
            }
        }
        CloseableHttpResponse response = null;
        String content = "";
        try {
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
            httpclient.close();
        }
        return content;
    }

    /***
     * 带参数和请求头的post body
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postBodyString(String url, String params, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(newRequestConfig);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");
        logger.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "POST", url, headers, params);
        String charSet = "UTF-8";
        StringEntity entity = new StringEntity(params, charSet);
        httpPost.setEntity(entity);
        if (null != headers) {
            for (String header : headers.keySet()) {
                String value = headers.get(header);
                httpPost.setHeader(header, value);
            }
        }
        CloseableHttpResponse response = null;
        String content = "";
        try {
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
            httpclient.close();
        }
        return content;
    }


//    /**
//     * 提交带有参数和文件的post 请求
//     *
//     * @param url
//     * @param fileName
//     * @return
//     * @throws IOException
//     */
//    public static String postFile(String url, String fileName) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        File file = new File(fileName);
//        FileBody fileBody = new FileBody(file);
//        MultipartEntity entity = new MultipartEntity();
//        //添加文件
//        entity.addPart("image", fileBody);
//        //添加参数
//        entity.addPart("name", new StringBody("jacky"));
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(newRequestConfig);
//        httpPost.setEntity(entity);
//        String content = "";
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(httpPost);
//            Integer code = response.getStatusLine().getStatusCode();
//            if (code == 200) {
//                content = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } finally {
//            if (null != response) {
//                response.close();
//            }
//            httpClient.close();
//        }
//        return content;
//    }

//    /**
//     * 提交带有参数和文件的post 请求
//     *
//     * @param url
//     * @return
//     * @throws IOException
//     */
//    public static String postFile(String url, File file, String fileParam, Map<String, Object> params) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        FileBody fileBody = new FileBody(file);
//        MultipartEntity entity = new MultipartEntity();
//        //添加文件
//        entity.addPart(fileParam, fileBody);
//        //添加参数
//        for (String param : params.keySet()) {
//            String value = (String) params.get(param);
//            entity.addPart(param, new StringBody(value));
//        }
//        HttpPost httpPost = new HttpPost(url);
//        httpPost.setConfig(newRequestConfig);
//        httpPost.setEntity(entity);
//        String content = "";
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(httpPost);
//            Integer code = response.getStatusLine().getStatusCode();
//            if (code == 200) {
//                content = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } finally {
//            if (null != response) {
//                response.close();
//            }
//            httpClient.close();
//        }
//        return content;
//    }
//
//    /**
//     * 提交带有参数和文件的post 请求
//     *
//     * @param url
//     * @return
//     * @throws IOException
//     */
//    public static String postFileWithHeader(String url, File file, String fileParam, Map<String, Object> params, Map<String, String> headers) throws IOException {
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        FileBody fileBody = new FileBody(file);
//        MultipartEntity entity = new MultipartEntity();
//        HttpPost httpPost = new HttpPost(url);
//        //添加文件
//        entity.addPart(fileParam, fileBody);
//        //添加参数
//        for (String param : params.keySet()) {
//            String value = (String) params.get(param);
//            entity.addPart(param, new StringBody(value));
//        }
//        //添加请求头
//        for (String header : headers.keySet()) {
//            String value = headers.get(header);
//            httpPost.setHeader(header, value);
//        }
//        logger.info("【OCR 请求地址:{}|,请求参数:{}|请求头:{}】",url,params,headers);
//        httpPost.setConfig(newRequestConfig);
//        httpPost.setEntity(entity);
//        String content = "";
//        CloseableHttpResponse response = null;
//        try {
//            response = httpClient.execute(httpPost);
//            Integer code = response.getStatusLine().getStatusCode();
//            if (code == 200) {
//                content = EntityUtils.toString(response.getEntity(), "UTF-8");
//            }
//        } finally {
//            if (null != response) {
//                response.close();
//            }
//            httpClient.close();
//        }
//        return content;
//    }

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

    /**
     * @param clazz
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     * @Author shiyj
     * @Description 健康湖州用户登录实现将post请求返回的jaso数据转换为JavaBean
     */
    public static <T> T postForm(String url, Map<String, String> params, Map<String, String> headers, Class<T> clazz) throws IOException {
        String response = postForm(url, params, headers);
        logger.info("预约挂号POST相关接口返回:{}", response);
        return JSON.parseObject(response, clazz);
    }

    /**
     * @param clazz
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     * @Author shiyj
     * @Description 健康湖州接口实现将get请求返回的jaso数据转换为JavaBean
     */
    public static <T> T get(String url, Map<String, String> params, Map<String, String> headers, Class<T> clazz) throws Exception {
        String response = get(url, params, headers);
        logger.info("预约挂号GET相关接口返回:{}", response);
        return JSON.parseObject(response, clazz);
    }
    
    /**
     * 请求参数string转map
     * @param strUrlParam
     * @return
     */
	public static Map<String, Object> getMapFromQueryString(String strUrlParam) {
		Map<String, Object> mapRequest = new HashMap<String, Object>();
		String[] arrSplit = null;
		arrSplit = strUrlParam.split("[&]");
		for (String strSplit : arrSplit) {
			String[] arrSplitEqual = null;
			arrSplitEqual = strSplit.split("[=]");
			// 解析出键值
			if (arrSplitEqual.length > 1) {
				// 正确解析
				mapRequest.put(arrSplitEqual[0], arrSplitEqual[1]);
			} else {
				if (arrSplitEqual[0] != "") {
					// 只有参数没有值，不加入
					mapRequest.put(arrSplitEqual[0], "");
				}
			}
		}
		
		return mapRequest;
	}
	
	
	/**
	 * aliyun
	 */
	/**
     * get
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doGet(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }

    /**
     * post form
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param bodys
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      Map<String, String> bodys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (bodys != null) {
            List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();

            for (String key : bodys.keySet()) {
                nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
            }
            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
            formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
            request.setEntity(formEntity);
        }

        return httpClient.execute(request);
    }

    /**
     * Post String
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (!StringUtils.isEmpty(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    /**
     * Post stream
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Put String
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     String body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (!StringUtils.isEmpty(body)) {
            request.setEntity(new StringEntity(body, "utf-8"));
        }

        return httpClient.execute(request);
    }

    /**
     * Put stream
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @param body
     * @return
     * @throws Exception
     */
    public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     byte[] body)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        if (body != null) {
            request.setEntity(new ByteArrayEntity(body));
        }

        return httpClient.execute(request);
    }

    /**
     * Delete
     *
     * @param host
     * @param path
     * @param method
     * @param headers
     * @param querys
     * @return
     * @throws Exception
     */
    public static HttpResponse doDelete(String host, String path, String method,
                                        Map<String, String> headers,
                                        Map<String, String> querys)
            throws Exception {
        HttpClient httpClient = wrapClient(host);

        HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
            request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }

    private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
        StringBuilder sbUrl = new StringBuilder();
        sbUrl.append(host);
        if (!StringUtils.isEmpty(path)) {
            sbUrl.append(path);
        }
        if (null != querys) {
            StringBuilder sbQuery = new StringBuilder();
            for (Map.Entry<String, String> query : querys.entrySet()) {
                if (0 < sbQuery.length()) {
                    sbQuery.append("&");
                }
                if (StringUtils.isEmpty(query.getKey()) && !StringUtils.isEmpty(query.getValue())) {
                    sbQuery.append(query.getValue());
                }
                if (!StringUtils.isEmpty(query.getKey())) {
                    sbQuery.append(query.getKey());
                    if (!StringUtils.isEmpty(query.getValue())) {
                        sbQuery.append("=");
                        sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                    }
                }
            }
            if (0 < sbQuery.length()) {
                sbUrl.append("?").append(sbQuery);
            }
        }

        return sbUrl.toString();
    }

    private static HttpClient wrapClient(String host) {
        HttpClient httpClient = new DefaultHttpClient();
        if (host.startsWith("https://")) {
            sslClient(httpClient);
        }

        return httpClient;
    }

    private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] xcs, String str) {

                }
                public void checkServerTrusted(X509Certificate[] xcs, String str) {

                }
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
    /***
     * 带参数和请求头的post body
     * @param url
     * @param params
     * @param headers
     * @return
     * @throws IOException
     */
    public static String postZuolin(String url, String formData, Map<String, String> headers) throws IOException {
        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建http POST请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(newRequestConfig);
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36");
        httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Accept", "application/json");
        logger.info("请求方式:[{}],请求地址:[{}],请求头:{},请求参数:{}", "POST", url, headers, formData);
        if(org.apache.commons.lang3.StringUtils.isNotEmpty(formData)){
        	httpPost.setEntity(new StringEntity(formData));
        }
        
        if (null != headers) {
            for (String header : headers.keySet()) {
                String value = headers.get(header);
                httpPost.setHeader(header, value);
            }
        }
        CloseableHttpResponse response = null;
        String content = "";
        try {
            response = httpclient.execute(httpPost);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                content = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } finally {
            if (null != response) {
                response.close();
            }
            httpclient.close();
        }
        return content;
    }
}
