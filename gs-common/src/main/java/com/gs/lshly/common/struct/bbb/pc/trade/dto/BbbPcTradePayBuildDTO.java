package com.gs.lshly.common.struct.bbb.pc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author oy
* @since 2020-11-04
*/
public abstract class BbbPcTradePayBuildDTO implements Serializable {

    @Data
    @ApiModel("BbbPcTradePayBuildDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("交易ID")
        private String tradeId;

        @ApiModelProperty("支付方式 10微信20支付宝")
        private Integer payType;

        @ApiModelProperty("用户优惠卷ID")
        private String cardId;
    }

    @Data
    @ApiModel("BbbPcTradePayBuildDTO.NotifyVO")
    @Accessors(chain = true)
    public static class NotifyVO implements Serializable {

        /**
         * 字符集
         */
        private String charset;
        /**
         * 版本号
         */
        private String version;
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
        private String signType;
        /**
         * 临时密钥
         */
        private String secretKey;
        /**
         * 接口类型
         */
        private String service;
        /**
         * 支付结果
         */
        private String status;
        /**
         * 返回码
         */
        private String returnCode;
        /**
         * 返回码描述信息
         */
        private String returnMessage;
        /**
         * 流水号
         */
        private String tradeNo;
        /**
         * 商户编号
         */
        private String merchantId;
        /**
         * 商户订单号
         */
        private String orderId;
        /**
         * 订单时间
         */
        private String orderTime;
        /**
         * 订单总金额
         */
        private String totalAmount;
        /**
         * 支付金额
         */
        private String payAmount;
        /**
         * 支付时间
         */
        private String payTime;
        /**
         * 会计日期
         */
        private String acDate;
        /**
         * 费用
         */
        private String fee;
        /**
         * 原样返回的商户数据
         */
        private String backParam;
        /**
         * 支付银行
         */
        private String bankAbbr;
        /**
         * 失败原因
         */
        private String failMsg;
    }

}
