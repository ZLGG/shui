package com.lakala.boss.api.entity.request;

import lombok.Data;
import lombok.ToString;

/**
 * 子订单明细
 *
 * @author: LXF
 * @version: V1.0.0
 * @Project: lkl_boss_sdk
 * @Package: com.lakala.boss.api.request
 * @Description: 子订单信息
 * @date: 2019-10-11 下午2:43:05
 */
@Data
@ToString
public class OrderDetail {

    /**
     * 订单序号  订单顺序号,只能上送数字
     */
    private String orderSeqNo;

    /**
     * 子订单号 商户的子订单号，保证全局唯一，不能与订单号重复
     */
    private String detailOrderId;

    /**
     * 子订单金额 子订单金额，以分为单位，等于子订单支付金额与子订单手续费金额和,如果是按比例分账，此信息域上送分账比例，如40.75%上送为4075，我方收到后做相应缩小
     */
    private String orderAmt;

    /**
     * 手续费金额 分账手续费，如果上送，将按照此手续费计算，商户平台不需要指定手续费时，默认为0
     */
    private String shareFee;

    /**
     * 子订单收款方商户编号
     */
    private String rcvMerchantId;

    /**
     * 子订单收款方商户展示名称
     */
    private String rcvMerchantIdName;

    /**
     * 商品展示url
     */
    private String showUrl;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品编号 只能是数字与字母
     */
    private String productId;

    /**
     * 商品描述
     */
    private String productDesc;

}
