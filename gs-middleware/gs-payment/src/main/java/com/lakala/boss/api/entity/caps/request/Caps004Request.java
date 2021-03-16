package com.lakala.boss.api.entity.caps.request;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author : L.T.P
 * @version V1.0.0
 * @Project: lkl-newboss-parent
 * @Package com.lakala.boss.api.entity.CapsRequest
 * @Description: 合并代付
 * @date Date : 2019年12月28日 14:34
 */
@Data
@ToString
public class Caps004Request {
    /**
     * 商户订单号
     */
    private String orderId;
    /**
     * 收款方手机号
     */
    private String mobileNumber;
    /**
     * 账户类型01-对公
     * 02-对私借记卡
     * 03 对私贷记卡
     */
    private String cardType;
    /**
     * 收款方银行卡号
     */
    private String cardNo;
    /**
     * 收款方银行户名
     */
    private String cardName;
    /**
     * 币种
     */
    private String currency;
    /**
     * 代付金额
     */
    private String capAmount;
    /**
     * 银行编码
     */
    private String bankCode;
    /**
     * 开户行联行号
     */
    private String bankLinkNo;
    /**
     * 开户行名称
     */
    private String bankName;
    /**
     * 付款方账号
     */
    private String payAccount;
    /**
     * 付款方账户名称
     */
    private String payAccountName;
    /**
     * 出资方式1-金额
     * 2-比例
     */
    private String payflg;
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
