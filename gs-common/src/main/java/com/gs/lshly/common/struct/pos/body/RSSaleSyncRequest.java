package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApiModel("销售单同步请求")
public class RSSaleSyncRequest {
    @ApiModelProperty("结算时间")
    private Date settleTime;
    @ApiModelProperty("收银机编号")
    private String posNo;
    @ApiModelProperty("收银员")
    private UCN cashier;
    @ApiModelProperty("会员")
    private RSSaleMember member;
    @ApiModelProperty("件数")
    private BigDecimal qty;
    @ApiModelProperty("小计")
    private BigDecimal amount;
    @ApiModelProperty("整单优惠")
    private BigDecimal discountAmount;
    @ApiModelProperty("找零金额")
    private BigDecimal changeAmount;
    @ApiModelProperty("班次名称")
    private String shiftName;
    @ApiModelProperty("班次时间")
    private Date shiftDate;
    @ApiModelProperty("商品明细")
    private List<RSSaleLine> lines = new ArrayList<>();
    @ApiModelProperty("支付明细")
    private List<RSSalePayment> payments = new ArrayList<>();
    @ApiModelProperty("整单优惠明细")
    private List<RSSaleFavItem> favItems = new ArrayList<>();
    @ApiModelProperty("销售单获得的积分")
    private RSSalePoint acquirePoints;
    @ApiModelProperty("收款设备")
    private RSReceiptDevice device;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("来源单据")
    private RSSourceBill sourceBill;
}