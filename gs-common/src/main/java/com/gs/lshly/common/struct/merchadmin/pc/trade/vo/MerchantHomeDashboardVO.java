package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.merchadmin.pc.foundation.vo.PCMerchDataNoticeVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantHomeDashboardVO implements Serializable {

    @Data
    @ApiModel("MerchantHomeDashboardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{
        /**
         * 我的工作台
         * */
        @ApiModelProperty("待发货订单")
        private Integer consignmentTradeCount;

        @ApiModelProperty("昨日订单")
        private Integer yesterdayTradeCount;

        @ApiModelProperty("昨日订单金额")
        private BigDecimal yesterdayTradeAmount;

        @ApiModelProperty("昨日UV")
        private Integer yesterdayUV;

        /**
         * 店铺的信息
         * */

        @ApiModelProperty("店铺Log")
        private String shopLogo;

        @ApiModelProperty("店铺名")
        private String merchantName;

        @ApiModelProperty("店铺描述")
        private String shopDesc;

        @ApiModelProperty("店铺邀请码")
        private String shopShareCode;


        /**
         * 昨日店铺转化
         * */

        @ApiModelProperty("访问量")
        private Integer visitCount;

        @ApiModelProperty("下单数")
        private Integer  tradeCount;

        @ApiModelProperty("销售额")
        private BigDecimal  salesVolume;

        /**
         * 交易
         * */


        @ApiModelProperty("未付款订单")
        private Integer noPayTrade;

        @ApiModelProperty("待发货订单")
        private Integer  pendingTrade;

        @ApiModelProperty("待收货订单")
        private Integer  receivedTrade;

        /**
         * 今日订单
         * */
        @ApiModelProperty("今日订单")
        private Integer  todayTrade;

        /**
         * 售后
         * */

        @ApiModelProperty("待处理退货")
        private Integer pendingReturn;

        @ApiModelProperty("待处理换货")
        private Integer  pendingExchange;

        /**
         * 商品
         * */

        @ApiModelProperty("未上架商品")
        private Integer noSaleGoods;

        @ApiModelProperty("库存预警")
        private Integer  inventoryWarning;

        @ApiModelProperty("审核失败")
        private Integer examineFail;

        @ApiModelProperty("商品咨询")
        private Integer  goodsConsultation;

        @ApiModelProperty("商品评价")
        private Integer goodsComment;


        /**
         * 昨日销量排行
         * */

        @ApiModelProperty("商品信息及数量")
        private List<GoodsInfo>  goodsInfos;

        /**
         * 通知
         * */
        @ApiModelProperty("通知")
        private List<PCMerchDataNoticeVO.ListVO> notices;

    }

    @Data
    @ApiModel("MerchantHomeDashboardVO.notice")
    @Accessors(chain = true)
    public static class notice implements Serializable{

        @ApiModelProperty("通知信息")
        private String message;

        @ApiModelProperty("通知时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private String noticeDate;
    }

    @Data
    @ApiModel("MerchantHomeDashboardVO.GoodsInfo")
    @Accessors(chain = true)
    public static class GoodsInfo implements Serializable{
        @ApiModelProperty("商品ID")
        private String id;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("销量")
        private Integer goodsSalesVolume;
    }

    public static void main(String[] args) {
        DecimalFormat percent = new DecimalFormat("0.00%");
       double completed_num = (double) 2/3;
        String format = percent.format(completed_num);
        System.out.println(format);
    }




}
