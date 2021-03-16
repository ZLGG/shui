package com.gs.lshly.common.struct.pos.body;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OOnlineOrderR {
    /** 单号 */
    private String number;
    /** 原单类型 ExpressDeliverOrder 快递; ShopDeliverOrder 配送; PickUpInStoreOrder 自提 */
    private String orderType;
    /** 原单单号 */
    private String orderNumber;
    /** 状态 SUBMITTED 已提交 FINISHED 已完成 CANCELED 已取消*/
    private String state;
    /** 申请时间 */
    private Date applyTime;
    /** 售后类型。 replacement 换货; refundOnly 只退款; returns 退货退款; cancel 取消订单 */
    private String afterSalesType;
    /** 售后原因 */
    private String afterSalesReason;
    /** 顾客 */
    private OCustomer customer;
    /** 商品总数 */
    private int qty;
    /** 退款总计。包含换货差额 */
    private BigDecimal amount = BigDecimal.ZERO;
    /** 退换货商品行 */
    private List<OOnlineOrderRLine> lines;
    /** 顾客备注 */
    private String customerRemark;
    /** 备注 */
    private String remark;
}
