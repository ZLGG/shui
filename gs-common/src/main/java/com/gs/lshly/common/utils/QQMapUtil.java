package com.gs.lshly.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.dubbo.common.utils.StringUtils;
import org.springframework.util.ObjectUtils;

import javax.net.ssl.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class QQMapUtil {
    /**
     * 通过腾讯地图将经纬度转成详细地址
     *
     * @param lat 纬度
     * @param lng 经度
     * @return
     */

    public static JSONObject getLatAndLng(BigDecimal lat, BigDecimal lng) {
        String key="6JCBZ-BO7W4-7LLUL-XWFOQ-UCYUE-TKBH4";
        try {
            String hsUrl = "https://apis.map.qq.com/ws/geocoder/v1/?location="+lat+","+lng+"&key="+key+"&get_poi=1";

            URL url;

            url = new URL(hsUrl);
            HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
            con.setRequestMethod("GET");// 提交模式
            X509TrustManager xtm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {

                    return null;
                }

                @Override
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {


                }

                @Override
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                        throws CertificateException {


                }
            };

            TrustManager[] tm = {xtm};

            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, tm, null);

            con.setSSLSocketFactory(ctx.getSocketFactory());
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });


            InputStream inStream = con.getInputStream();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
            }
            byte[] b = outStream.toByteArray();//网页的二进制数据
            outStream.close();
            inStream.close();
            String rtn = new String(b, "utf-8");
            if (StringUtils.isNotEmpty(rtn)) {
                JSONObject object = JSONObject.parseObject(rtn);
                System.out.println(object+"~~~~~~~~~~~~~~~~~~~~~~~");
                if (object != null) {
                    if ((!ObjectUtils.isEmpty(object.getObject("status",String.class))) && object.getObject("status",Integer.class) == 0) {
                        JSONObject result = object.getJSONObject("result");
                        if (result != null) {
                            JSONObject addressComponent = result.getJSONObject("address_component");
                            if (addressComponent != null) {
                                return addressComponent;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
