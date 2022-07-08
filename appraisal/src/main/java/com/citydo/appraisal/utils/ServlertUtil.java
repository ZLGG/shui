package com.citydo.appraisal.utils;

import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * http: request response 过滤XSS SQL 数据工具类
 *
 * @author limk
 * @date 2018-05-04 15:42
 */
public class ServlertUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServlertUtil.class);

    private static final String AJAX = "XMLHttpRequest";
    private static final String STR_BODY = "body";

    /**
     * 是否是Ajax请求
     */
    public static boolean isAjax(ServletRequest request) {
        String requestedWith = ((HttpServletRequest)request).getHeader("X-Requested-With");
        return requestedWith != null && requestedWith.equalsIgnoreCase(AJAX);
    }

    /**
     * 读取request 已经被防止XSS，SQL注入过滤过的 请求参数key 对应的value
     */
    public static String getParameter(ServletRequest request, String key) {
        return ((HttpServletRequest)request).getParameter(key);
    }

    /**
     * 取request中的已经被防止XSS，SQL注入过滤过的key value数据封装到map 返回
     */
    public static Map<String, String> getRequestParameters(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>(16);
        Enumeration<String> enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = enums.nextElement();
            String paraValue = ((HttpServletRequest)request).getParameter(paraName);
            if (null != paraValue && !"".equals(paraValue)) {
                dataMap.put(paraName, paraValue);
            }
        }
        return dataMap;
    }

    /**
     * 读取request 已经被防止XSS，SQL注入过滤过的 请求头key 对应的value
     */
    public static String getHeader(ServletRequest request, String key) {
        return ((HttpServletRequest)request).getHeader(key);
    }

    /**
     * 取request头中的已经被防止XSS，SQL注入过滤过的 key value数据封装到map 返回
     */
    public static Map<String, String> getRequestHeaders(ServletRequest request) {
        Map<String, String> headerMap = new HashMap<>(16);
        Enumeration<String> enums = ((HttpServletRequest)request).getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = enums.nextElement();
            String value = ((HttpServletRequest)request).getHeader(name);
            if (null != value && !"".equals(value)) {
                headerMap.put(name, value);
            }
        }
        return headerMap;
    }

    /**
     * 封装response 统一json返回
     */
    public static void write(String outStr, ServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = null;
        try {
            HttpServletResponse req = (HttpServletResponse) response;
            printWriter = req.getWriter();
            printWriter.write(outStr);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

    /**
     * 封装response 统一json返回
     */
    public static void outputSteam(String outStr, ServletResponse response){
        response.setContentType("application/json;charset=utf-8");
        OutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(outStr.getBytes("UTF-8"));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 获取浏览器属性
     */
    public static UserAgent getUserAgent(ServletRequest request) {
        return UserAgentUtil.parse(((HttpServletRequest)request).getHeader("User-Agent"));
    }

    /**
     * 是否是安卓请求
     */
    public static boolean isMobile(ServletRequest request) {
        UserAgent userAgent = UserAgentUtil.parse(((HttpServletRequest)request).getHeader("User-Agent"));
        return userAgent.isMobile();
    }

    /**
     * 是否是浏览器请求
     */
    public static boolean isBrowser(ServletRequest request) {
        UserAgent userAgent = UserAgentUtil.parse(((HttpServletRequest)request).getHeader("User-Agent"));
        return !userAgent.isMobile();
    }

}
