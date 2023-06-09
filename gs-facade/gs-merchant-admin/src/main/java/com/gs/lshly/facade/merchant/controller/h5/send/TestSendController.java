package com.gs.lshly.facade.merchant.controller.h5.send;

import com.alibaba.fastjson.JSONObject;
import com.gs.lshly.common.struct.platadmin.trade.dto.MoziSMSDTO;
import org.apache.http.HttpResponse;
import com.google.common.hash.Hashing;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.rpc.api.merchadmin.pc.trade.IPCMerchTradeGoodsRpc;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.binary.Hex;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.util.HashMap;
import java.util.Map;


/**
 */
@RestController
@RequestMapping("/merchadmin/mozi/sms")
@Api(tags = "测试摩字")
public class TestSendController {

        @DubboReference
        private IPCMerchTradeGoodsRpc ipcMerchTradeGoodsRpc;

        @ApiOperation("测试接口")
        @PostMapping("/send/{mobiles},{sign},{parameter},{merchantOrderId}")
        public String testDete(@PathVariable String mobiles ,@PathVariable String sign
                                                            ,@PathVariable String parameter , @PathVariable String merchantOrderId) {
            return ipcMerchTradeGoodsRpc.testDete(mobiles,sign,parameter,merchantOrderId);
        }

        @ApiOperation("内容发送")
        @PostMapping("/send/content")
        public String sendContentDate(MoziSMSDTO.SendContentDTO sendContent) {
            return ipcMerchTradeGoodsRpc.sendContentDate(sendContent);
        }

        @ApiOperation("模板发送")
        @PostMapping("/send/TemplateDate")
        public String TemplateDate(MoziSMSDTO.TemplateDTO templateDTO) {
            return ipcMerchTradeGoodsRpc.TemplateDate(templateDTO);
        }

        @ApiOperation("余额查询")
        @PostMapping(value = "/{merchantId}")
        public String balanceQueryDate(MoziSMSDTO.BalanceQueryDTO balanceQueryDTO) {
            return ipcMerchTradeGoodsRpc.balanceQueryDate(balanceQueryDTO);
        }

        /**
         * 生成请求签名
         *
         * @param data body 数据
         * @param apikey apikey
         * @return
         */
        private static String buildSign(String data, String apikey)  throws Exception {
                return Hex.encodeHexString(Hashing.sha1().hashBytes((data + apikey).getBytes("UTF-8")).asBytes());
        }


        //查看当前jvm支持的通信协议：
        public static   String[] protocols() throws Exception {
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, null, null);

            SSLSocketFactory factory = (SSLSocketFactory) context.getSocketFactory();
            SSLSocket socket = (SSLSocket) factory.createSocket();

            String[] protocols = socket.getSupportedProtocols();

            System.out.println("Supported Protocols: " + protocols.length);
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(" " + protocols[i]);
            }

            protocols = socket.getEnabledProtocols();

            System.out.println("Enabled Protocols: " + protocols.length);
            for (int i = 0; i < protocols.length; i++) {
                System.out.println(" " + protocols[i]);
            }

            return protocols;
        }


//        /**
//         * 调用对方接口方法
//         * @param path 对方或第三方提供的路径
//         * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
//         */
//        public static void interfaceUtil(String path,String data) {
//            try {
//                URL url = new URL(path);
//                //打开和url之间的连接
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                PrintWriter out = null;
//
//                /**设置URLConnection的参数和普通的请求属性****start***/
//
//                conn.setRequestProperty("accept", "*/*");
//                conn.setRequestProperty("connection", "Keep-Alive");
//                conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
//
//                /**设置URLConnection的参数和普通的请求属性****end***/
//
//                //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
//                //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
//                //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
//                conn.setDoOutput(true);
//                conn.setDoInput(true);
//
//                conn.setRequestMethod("POST");//GET和POST必须全大写
//                /**GET方法请求*****start*/
//                /**
//                 * 如果只是发送GET方式请求，使用connet方法建立和远程资源之间的实际连接即可；
//                 * 如果发送POST方式的请求，需要获取URLConnection实例对应的输出流来发送请求参数。
//                 * */
//                conn.connect();
//
//                /**GET方法请求*****end*/
//
//                /***POST方法请求****start*/
//
//                out = new PrintWriter(conn.getOutputStream());//获取URLConnection对象对应的输出流
//
//                out.print(data);//发送请求参数即数据
//
//                out.flush();//缓冲数据
//                /***POST方法请求****end*/
//
//                //获取URLConnection对象对应的输入流
//                InputStream is = conn.getInputStream();
//                //构造一个字符流缓存
//                BufferedReader br = new BufferedReader(new InputStreamReader(is));
//                String str = "";
//                while ((str = br.readLine()) != null) {
//                    str=new String(str.getBytes(),"UTF-8");//解决中文乱码问题
//                    System.out.println(str);
//                }
//                //关闭流
//                is.close();
//                //断开连接，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
//                //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
//                conn.disconnect();
//                System.out.println("完整结束");
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }

        //模板发送
        private static String templateUrl = "https://api.mozisms.com/sms/send/template";
        //短信内容发送
        private static String contentUrl = "https://api.mozisms.com/sms/send/content";
        //余额查询
        private static String statusUrl = "https://api.mozisms.com/sms/status/batchQuery/v2";

        //商户号
        private static String merchantId = "1002fc";
        //apikey
        private static String merchantApiKey = "RiK4Di";




        public static void main(String[] args) throws Exception {
            String responseTxt="";
            Map<String,Object> param = new HashMap<String,Object>();
            //商户订单号
            String merchantOrderId = "a123456";
            //短信内容
            String sign = "摩字";
            //支持11位手机号,一次最多100个手机号，使用英文,分隔
            String mobiles = "13027710907";
            String content = "验证码是#code#，您在测试短信服务，如非本人操作，请忽略。";

            param.put("merchantOrderId", merchantOrderId);
            param.put("sign", sign);
            param.put("content", content);
            param.put("mobiles", mobiles);
            String message = JSONObject.toJSONString(param);
            System.out.println("message:" + message);
            try{
                HttpPost request = new HttpPost(contentUrl.trim());
                setPostHead(request, buildSign(message, Merchant.merchantApiKey));
                request.setEntity(new StringEntity(message, HTTP.UTF_8));
                HttpResponse response = HttpsUtil.getClient().execute(request);
                Integer code = response.getStatusLine().getStatusCode();
                if (200 == code) {
                    responseTxt = EntityUtils.toString(response.getEntity(), "utf-8");
                } else {
                    responseTxt = EntityUtils.toString(response.getEntity(), "utf-8");
                }
                System.out.println(responseTxt);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public static void setPostHead(HttpUriRequest request, String sign) {
            request.setHeader("Accept", "application/json;charset=utf-8;");
            request.setHeader("Content-Type", "application/json;charset=utf-8;");
            request.setHeader("merchantId", Merchant.merchantId);
            request.setHeader("sign", sign);
        }

        public static class Merchant {
            private static String merchantId = "1002fc";
            private static String merchantApiKey = "RiK4Di";

            //卫盈智信-通知
            private static String merchantId2= "1002fb";
            private static String merchantApiKey2 = "zNs8i6";

        }

}
