package com.lakala.boss.api.entity.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * User: gtli
 * Date: 2019-11-20
 * Time: 14:40
 * Description: No Description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class StandardAPDownLoadFileResponse extends BaseResponse {

    /**
     * 字符集
     */
    private String charset = "00";
    /**
     * 接口版本
     */
    private String version = "1.0";

    /**
     * 服务器证书
     */
    private String serverCert;

    /**
     * 服务器签名
     */
    private String serverSign;

    /**
     * 签名方式
     */
    private String signType = "RSA";

    /**
     * 接口类型
     */
    private String service = "StandardAPDownLoadFile";

    /**
     * 流水号
     */
    private String tradeNo;

    /**
     * 商户编号
     */
    private String merchantId;

    /**
     * 对账文件URL
     */
    private String downloadUrl;

    /**
     * 文件Mac
     */
    private String fileMac;
}
