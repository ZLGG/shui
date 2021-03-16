package com.lakala.boss.api.utils;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.gs.lshly.common.utils.HttpUtil;
import com.gs.lshly.common.utils.HttpsUtil;
import com.lakala.boss.api.common.Common;
import com.lakala.boss.api.entity.request.BaseRequest;
import com.lakala.boss.api.entity.response.BaseResponse;
import com.lakala.boss.api.security.RSASignUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Project: boss-sdk
 * @Package: com.lakala.boss.api.utils
 * @Description: 接口调用工具类
 * @author: LXF
 * @date Date: 2019年10月23日 18:00
 * @version: V1.0.0
 */
@Slf4j
public class BossClient {

    private RSASignUtil rsaSignUtil;

    private String serverUrl;

    public BossClient(String merchantCertPath, String merchantCertPass, String serverUrl) {
        try {
            this.rsaSignUtil = new RSASignUtil(merchantCertPath, merchantCertPass);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        this.serverUrl = serverUrl;
    }

    /**
     * 调用接口
     * @param request
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T extends BaseResponse> T execute(BaseRequest<T> request) throws Exception {
        Map<String, Object> paramsMap = MapConverter.beanToMap(request);
        paramsMap.remove("responseClass");
        // 剔空排序
        String sf = rsaSignUtil.coverMap2String(paramsMap);
        // 签名
        try {
            String merchantSign = rsaSignUtil.sign(sf, Common.CHAR_SET_GBK);
            paramsMap.put("merchantSign", merchantSign);
        }catch (Exception e){
            e.printStackTrace();
        }
        String responseStr = doPost(paramsMap, Common.CHAR_SET_GBK);
        return doResponse(request, responseStr);
    }

    /**
     * 处理参数
     *
     * @param paramsMap
     */
    private void doBuildParams(Map<String, Object> paramsMap) {
        paramsMap.remove("responseClass");
        // 剔空排序
        String sf = rsaSignUtil.coverMap2String(paramsMap);
        System.out.println("加签原文: "+sf);
        // 签名
        try {
            System.out.println("签名start1");
            String merchantSign = rsaSignUtil.sign(sf, Common.CHAR_SET_GBK);
            System.out.println("签名end2");
            paramsMap.put("merchantSign", merchantSign);
            System.out.println("签名:"+merchantSign);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 处理请求
     *
     * @param paramsMap
     * @return
     * @throws Exception
     */
    private String doPost(Map<String, Object> paramsMap, String charset) throws Exception {
        log.info("doPost...");
        String jsonStrParams = JSONObject.toJSON(paramsMap).toString();
        log.info("jsonStrParams: " + jsonStrParams);
        String responseStr;
        if (serverUrl != null && serverUrl.toLowerCase().startsWith("https")) {
            responseStr = HttpsUtil.doPost(serverUrl, jsonStrParams, charset);
        } else {
            responseStr = HttpUtil.doPost(serverUrl, jsonStrParams, charset);
        }
        log.info("responseStr: " + responseStr);
        return responseStr;
    }

    /**
     * 处理响应
     *
     * @param request
     * @param responseStr
     * @return
     */
    @SuppressWarnings("unchecked")
    private <T extends BaseResponse> T doResponse(BaseRequest<T> request, String responseStr) {
        if (StrUtil.isBlank(responseStr)) {
            return null;
        } else if (responseStr.contains("returnCode")) {
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            T response = (T) JSONObject.parseObject(responseStr,request.getResponseClass());
                    response.setSuccess(doInvokeResult(jsonObject.getString("returnCode"), responseStr));
            return response;
        } else if (responseStr.contains("respCode")) {
            JSONObject jsonObject = JSONObject.parseObject(responseStr);
            jsonObject.put("returnCode", jsonObject.get("respCode"));
            jsonObject.put("returnMessage", jsonObject.get("respMsg"));
            T response = (T) JSONObject.parseObject(responseStr, request.getResponseClass());
            response.setSuccess(doInvokeResult(jsonObject.getString("returnCode"), responseStr));
            return response;
        } else {
            return null;
        }
    }

    /**
     * 验签
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    private boolean verify(String responseJsonStr) {
        if (StrUtil.isNotBlank(responseJsonStr)) {
            JSONObject jsonObject = JSONObject.parseObject(responseJsonStr);
            TreeMap<String, Object> map = (TreeMap<String, Object>) JSONObject.parseObject(responseJsonStr, TreeMap.class);
            String serverCert = String.valueOf(map.get("serverCert"));
            String serverSign = String.valueOf(map.get("serverSign"));
            String returnCode = String.valueOf(map.get("returnCode"));
            if (StrUtil.isNotBlank(returnCode) && !returnCode.contains("IPS")) {
                doVerifyParams(map);
                // 验签
                String oriData = rsaSignUtil.coverMap2String(map);
//                System.out.println("oriData:" + oriData);
                return rsaSignUtil.verify(oriData, serverSign, serverCert, Common.CHAR_SET_GBK);
            }
        }
        return false;
    }

    /**
     * 验签
     * @param obj
     * @return
     */
    public boolean verify(Object obj) throws Exception {
        if (ObjectUtil.isNotEmpty(obj)) {
            Map<String, Object> map = MapConverter.beanToMap(obj);
            String serverCert = String.valueOf(map.get("serverCert"));
            String serverSign = String.valueOf(map.get("serverSign"));
            // 验签
            String oriData = rsaSignUtil.coverMap2String(map);
            System.out.println("oriData:" + oriData);
            return rsaSignUtil.verify(oriData, serverSign, serverCert, Common.CHAR_SET_GBK);
        }
        return false;
    }

    /**
     * 请求参数
     *
     * @return
     */
    public String getRequestJsonStr(BaseRequest request) throws Exception {
        Map<String, Object> paramsMap = MapConverter.beanToMap(request);
        doBuildParams(paramsMap);
        return JSONObject.toJSON(paramsMap).toString();
    }

    /**
     * 处理接口调用结果
     * @param returnCode
     */
    private boolean doInvokeResult(String returnCode, String responseJsonStr){
        if(StrUtil.isNotBlank(returnCode) && returnCode.endsWith("00000")){
            if(verify(responseJsonStr)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 清除不参与验签的数据
     * @param map
     */
    private void doVerifyParams(TreeMap<String, Object> map){
        map.remove("serverCert");
        map.remove("serverSign");

        String service = String.valueOf(map.get("service"));
        if(StrUtil.isBlank(service)){
            return;
        } else if(org.apache.commons.codec.binary.StringUtils.equals(service, "QRCodePaymentCommit")){
            map.remove("service");
            map.remove("orderId");
            map.remove("payInfo");
            map.remove("payOrdNo");
        } else if(org.apache.commons.codec.binary.StringUtils.equals(service, "EntRstSign")){
            map.remove("secretKey");
        }
    }

    /**
     * 返回RSA工具类
     * @return
     */
    public RSASignUtil getRsaSignUtil() {
        return rsaSignUtil;
    }
}