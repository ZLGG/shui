package com.lakala.boss.api.entity.caps.body;

import com.lakala.boss.api.entity.caps.request.Stakeholders;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 代付结果查询返回
 * @author liu_dd
 * @date 2019/12/26 4:44 下午
 * @version 1.0.0
 */
@Data
@ToString
public class QueryCapsRealPayResultResponseBody implements Serializable {

    private static final long serialVersionUID = -1170205755899486581L;
    /**
     * 商户订单号
     */
    private String orderId;
    /**
     * 支付订单号
     */
    private String paymentOrderNo;
    /**
     * 收款方手机号 密文
     */
    private String mobileNumber;
    /**
     * 帐号类型
     * 01-对公
     * 02-对私借记卡
     * 03 对私贷记卡
     */
    private String cardType;
    /**
     * 收款方银行卡号 密文
     */
    private String cardNo;
    /**
     * 收款方银行户名 密文
     */
    private String cardName;
    /**
     * 币种
     */
    private String currency;
    /**
     * 代付金额
     */
    private BigDecimal capAmount;
    /**
     * 银行编码，对公代付时必填
     */
    private String bankCode;
    /**
     * 开户行联行号，对公必填
     */
    private String bankLinkNo;
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 付款方账号 密文
     */
    private String payAccount;
    /**
     * 付款方账户名称 密文
     */
    private String payAccountName;
    /**
     * 出资方式
     */
    private String payflg;
    /**
     * 代付手续费
     */
    private BigDecimal capFee;
    /**
     * 代付状态
     */
    private String status;
    /**
     * 代付完成时间
     */
    private String capEndTime;
    /**
     * 预留字段1
     */
    private String backUpField1;
    /**
     * 预留字段2
     */
    private String backUpField2;
    /**
     * 预留字段3
     */
    private String backUpField3;
    /**
     * 出款帐号列表
     */
    private List<Stakeholders> stakeholders;
}
