package com.gs.lshly.facade.possync.controller;


import com.gs.lshly.common.response.ResponseData;
import com.gs.lshly.common.utils.HttpsUtil;
import com.gs.lshly.common.utils.MapToolsUtil;
import com.gs.lshly.common.utils.RSAUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;


/**
 * 示例工程
 * @author lxus
 * @since 2021-01-25
 */
@RestController
@RequestMapping("/pos-sync")
@Api(tags = "POS接口调用(参数签名)")
public class DemoController {

    private String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBALZrfh8iVLkjzM40rdSngIRqdxLxYBCeB32SL1r0/Ee6rNvWyBnltY/P3LxL0plfzKBt9EmdQPjR0lanOG6SC+BlBNVcL6wmRWMIsMd9G1RsL+pKurXbN0IF9899Ucvnme+SptuS1iBIbT6Z5wogu8gupLSPO9jefsyMDZrZBSpXAgMBAAECf002t8+pFJns/gEru8S4F49RL1MdAUToqWG674OvTakjZTrHPlIeT5LDF0VpTZx/THo1Q6JgFpsnAeuC3H7SNAsPzEiZnas7oyMR6G6/pE29cStZ8gE4KNqiHH5ZdzYUhDKcogJdaO4Q4vyZ+wFXvCcD4lwkwnzUnVSuMxY2XCECQQDqkbk33/JLeEtwZodBVmMOZyQrrcH6ICR+NCuSMWhoh7XK5t7X9CXIAc7sA7Ych4hrXrChf6r0IXVHBpo+8YJxAkEAxxYP9LtjLW0+7+E1U7DhP/S90iPs4ApfmkYIs1+beUnkJNrGfhL9KE59RJkhQzAOLHWzuiOVOhHtTMB9B65NRwJAILBQHH5D+Mp50N8o6C0OvtoWy1N6nc3O6BuDGutxvmdzKSSFV5j5jubZnEBkJ5OnqjbW7JqrCzyCaHR+GFl1sQJBAIdgBTGRSntVUfqj1TM3j7OBpKIEM8qUwzWQcdInLP1otW4qJdcM6oBB/wRqbCfXwYuR7cig8ZQvJIGQUnSqnXkCQQCY8V1cP/pjtn0e0NbpiUhBTrqX0u841D1DHYkgn3Git50DqF0cBp6/DPUaf1U2oyuOdfEjaENr9zrdTOi26jAx";


    private String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2a34fIlS5I8zONK3Up4CEancS8WAQngd9ki9a9PxHuqzb1sgZ5bWPz9y8S9KZX8ygbfRJnUD40dJWpzhukgvgZQTVXC+sJkVjCLDHfRtUbC/qSrq12zdCBffPfVHL55nvkqbbktYgSG0+mecKILvILqS0jzvY3n7MjA2a2QUqVwIDAQAB";

    private static String accessKeyId="longliangjc";

    private static String accessKeySecret="123456";
    @ApiOperation("获取测试接口公钥")
    @GetMapping("/test/pubKey")
    public ResponseData<Void> getTestPubKey() {
        return ResponseData.data(pubKey);
    }

    @ApiOperation("测试数据签名")
    @PostMapping("/test/sign")
    public ResponseData<Void> testSign(@RequestBody TreeMap<String, Object> params) {
        String signStr = RSAUtil.toSignStr(params);
        return ResponseData.data(RSAUtil.sign2Base64(privateKey, signStr));
    }

    @ApiOperation("测试校验数据签名")
    @PostMapping("/test/verifySign")
    public ResponseData<Void> verifyTestSign(@RequestBody TreeMap<String, Object> params, String sign) {
        String signStr = RSAUtil.toSignStr(params);
        System.out.println(signStr);
        System.out.println(pubKey);
        System.out.println(sign);
        boolean valid = RSAUtil.verifySign(pubKey, signStr, sign);
        return ResponseData.data(valid ? "验签通过" : "验签失败");
    }


    @ApiOperation("测试接收POS系统数据(提供接口供POS系统调用)")
    @PostMapping("/test/receiver")
    //todo 测试接收POS系统数据(提供接口供POS系统调用)
    public ResponseData<Void> testReceiver(@RequestBody TreeMap<String, Object> params) {

        //1, 数据验签(使用POS系统提供的公钥)

        //2, 持久化

        return ResponseData.data("");
    }
    /**
     * MD5加密
     * @param str 内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private static String MD5(String str, String charset) throws Exception {
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


    //todo 测试推送数据到POS系统(调用pos系统接口)
    public static void main(String[] args) {
        String requestUri="/dpos/v1/sku/create";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String body=null;
        String s=requestUri+body+timestamp+accessKeySecret;
        //3, 调用接口
        try {
            String signature = MD5(s, "UTF-8").toLowerCase();
            System.out.println(signature);
            String url="https://api.qianfan123.com/dpos/v1/sku/create?accessKeyId="+accessKeyId+"&timestamp="+System.currentTimeMillis()+"&signature="+signature+"&signMethod=MD5";
            System.out.println(url);

            String s1 = HttpsUtil.doPost(url, body);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
