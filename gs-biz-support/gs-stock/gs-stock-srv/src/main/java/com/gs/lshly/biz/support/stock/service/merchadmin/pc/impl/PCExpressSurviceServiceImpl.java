package com.gs.lshly.biz.support.stock.service.merchadmin.pc.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.gs.lshly.biz.support.stock.entity.StockKdniao;
import com.gs.lshly.biz.support.stock.repository.IStockKdniaoRepository;
import com.gs.lshly.biz.support.stock.service.merchadmin.pc.IPCExpressSurviceService;
import com.gs.lshly.common.enums.StockKdniaoEnum;
import com.gs.lshly.common.exception.BusinessException;
import com.gs.lshly.common.struct.platadmin.stock.dto.StockKdniaoDTO;
import com.gs.lshly.common.struct.platadmin.stock.vo.LogisticsInformationVO;
import com.gs.lshly.middleware.mybatisplus.MybatisPlusUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;
/**
* <p>
*  服务实现类
* </p>
 * @author zst
 * @since 2021-1-29
*/
@Component
public class PCExpressSurviceServiceImpl implements IPCExpressSurviceService {

    @Autowired
    private IStockKdniaoRepository iStockKdniaoRepository;

    //电商ID
    private String EBusinessID="1698847";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String AppKey="cf33f513-52c5-4a75-819b-ded2d3902e78";
    //请求url
    private String ReqURL="https://api.kdniao.com/Ebusiness/EbusinessOrderHandle.aspx";
    //http://sandboxapi.kdniao.com:8080/kdniaosandbox/gateway/exterfaceInvoke.json

    //电商ID
    private String onLineMonitoringEBusinessID="1703424";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    private String onLineMonitoringAppKey="0cc57067-459c-4915-9b89-75735a054d79";

    /**
     * 根据快递单号追踪信息
     * expressNumber 快递单号
     * customerName 顺丰参数
     * shipperCode 物流公司编号
     * @return
     */
    @Override
    public LogisticsInformationVO.ListVO orderLogisticsInformation(StockKdniaoDTO.TradeTailDTO dto) {
        //校验
        checkKdniao(dto);
        LogisticsInformationVO.ListVO listVO = new LogisticsInformationVO.ListVO();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String result = this.getOrderTracesByJson(dto);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.containsKey("ShipperCode")){
                String ShipperCode = jsonObject.getString("ShipperCode");
                String LogisticCode = jsonObject.getString("LogisticCode");
                JSONArray Traces = jsonObject.getJSONArray("Traces");
                System.out.print("Traces:"+Traces);
                listVO.setTraces(Traces);
                System.out.println("快递名称"+ShipperCode);
                System.out.println("快递单号"+LogisticCode);
                int count = 1;
                for(int i = 0; i < Traces.size(); i++) {
                    JSONObject object = (JSONObject) Traces.get(i);
                    String AcceptTime = object.getString("AcceptTime");
                    String AcceptStation = object.getString("AcceptStation");
                    System.out.println("时间："+AcceptTime+"\t"+AcceptStation);
                    map.put("时间"+count,AcceptTime+AcceptStation);
                    count++;
                }}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("结束");
        }
        return listVO;
    }

    private void checkKdniao (StockKdniaoDTO.TradeTailDTO dto){
        if(ObjectUtils.isEmpty(dto.getExpressNumber())){
            throw new BusinessException("快递单号数据为空");
        }
        if(ObjectUtils.isEmpty(dto.getShipperCode())){
            throw new BusinessException("物流公司编号为空");
        }
    }

    private String getOrderTracesByJson(StockKdniaoDTO.TradeTailDTO dto) throws Exception{
        QueryWrapper<StockKdniao> wrapper = MybatisPlusUtil.query();
        wrapper.eq("is_start", StockKdniaoEnum.开启.getCode());
        StockKdniao stockKdniao = iStockKdniaoRepository.getOne(wrapper);
        String requestData= "" ;
        if(dto.getShipperCode().equals("SF")){
            if(ObjectUtils.isEmpty(dto.getCustomerName())){
                throw new BusinessException("请输入收件人或者寄件人的手机号后四位数字");
            }
            requestData= "{'ShipperCode':'"  + dto.getShipperCode()
                    + "','LogisticCode':'" + dto.getExpressNumber() + "','CustomerName':'" + dto.getCustomerName() + "'}";
        }else{
            requestData= "{'ShipperCode':'"  +  dto.getShipperCode()
                    + "','LogisticCode':'" + dto.getExpressNumber() + "'}";
        }

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", stockKdniao.getEBusinessID());
        params.put("RequestType", "8002");
        String dataSign=encrypt(requestData, stockKdniao.getAppKey(), "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        return sendPost(ReqURL, params);
    }

    /**
     * MD5加密
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     * @param str 内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException{
        String encoded = base64Encode(str.getBytes(charset));
        return encoded;
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException{
        String result = URLEncoder.encode(str, charset);
        return result;
    }

    /**
     * 电商Sign签名生成
     * @param content 内容
     * @param keyValue Appkey
     * @param charset 编码方式
     * @throws UnsupportedEncodingException ,Exception
     * @return DataSign签名
     */
    @SuppressWarnings("unused")
    private String encrypt (String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception
    {
        if (keyValue != null)
        {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param params 请求的参数集合
     * @return 远程资源的响应结果
     */
    @SuppressWarnings("unused")
    private String sendPost(String url, Map<String, String> params) {
        OutputStreamWriter out = null;
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL realUrl = new URL(url);
            HttpURLConnection conn =(HttpURLConnection) realUrl.openConnection();
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // POST方法
            conn.setRequestMethod("POST");
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.connect();
            // 获取URLConnection对象对应的输出流
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            // 发送请求参数
            if (params != null) {
                StringBuilder param = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    if(param.length()>0){
                        param.append("&");
                    }
                    param.append(entry.getKey());
                    param.append("=");
                    param.append(entry.getValue());
                    //System.out.println(entry.getKey()+":"+entry.getValue());
                }
                //System.out.println("param:"+param.toString());
                out.write(param.toString());
            }
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
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
        return result.toString();
    }


    private static char[] base64EncodeChars = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };

    public static String base64Encode(byte[] data) {
        StringBuffer sb = new StringBuffer();
        int len = data.length;
        int i = 0;
        int b1, b2, b3;
        while (i < len) {
            b1 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                sb.append("==");
                break;
            }
            b2 = data[i++] & 0xff;
            if (i == len)
            {
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                sb.append("=");
                break;
            }
            b3 = data[i++] & 0xff;
            sb.append(base64EncodeChars[b1 >>> 2]);
            sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
            sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
            sb.append(base64EncodeChars[b3 & 0x3f]);
        }
        return sb.toString();
    }


    /**
     * 在途监控
     * @return
     */
    @Override
    public LogisticsInformationVO.ListVO onLineMonitoring(StockKdniaoDTO.TradeTailDTO dto) {
        if(ObjectUtils.isEmpty(dto.getExpressNumber())){
            throw new BusinessException("快递单号数据为空");
        }
        LogisticsInformationVO.ListVO listVO = new LogisticsInformationVO.ListVO();
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            String result = this.getOrderTracesByJson2(dto);
            System.out.println(result);
            JSONObject jsonObject = JSONObject.parseObject(result);
            if(jsonObject.containsKey("ShipperCode")){
                String ShipperCode = jsonObject.getString("ShipperCode");
                String LogisticCode = jsonObject.getString("LogisticCode");
                JSONArray Traces = jsonObject.getJSONArray("Traces");
                System.out.print("Traces:"+Traces);
                listVO.setTraces(Traces);
                System.out.println("快递名称"+ShipperCode);
                System.out.println("快递单号"+LogisticCode);
                int count = 1;
                for(int i = 0; i < Traces.size(); i++) {
                    JSONObject object = (JSONObject) Traces.get(i);
                    String AcceptTime = object.getString("AcceptTime");
                    String AcceptStation = object.getString("AcceptStation");
                    System.out.println("时间："+AcceptTime+"\t"+AcceptStation);
                    map.put("时间"+count,AcceptTime+AcceptStation);
                    count++;
                }}
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("结束");
        }
        return listVO;
    }



    private String getOrderTracesByJson2(StockKdniaoDTO.TradeTailDTO dto) throws Exception{
        QueryWrapper<StockKdniao> wrapper = MybatisPlusUtil.query();
        wrapper.eq("is_start", StockKdniaoEnum.开启.getCode());
        StockKdniao stockKdniao = iStockKdniaoRepository.getOne(wrapper);
        String requestData= "{'ShipperCode':'"  + dto.getShipperCode()
                                + "','LogisticCode':'" + dto.getExpressNumber() + "','CustomerName':'" + dto.getCustomerName() + "'}";

        System.out.println(requestData);

        Map<String, String> params = new HashMap<String, String>();
        params.put("RequestData", urlEncoder(requestData, "UTF-8"));
        params.put("EBusinessID", stockKdniao.getEBusinessID());
        //8001在途监控
        params.put("RequestType", "8001");
        String dataSign=encrypt(requestData, stockKdniao.getAppKey(), "UTF-8");
        params.put("DataSign", urlEncoder(dataSign, "UTF-8"));
        params.put("DataType", "2");

        String result=sendPost(ReqURL, params);

        //根据公司业务处理返回的信息......

        return result;
    }

}
