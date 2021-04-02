package com.gs.lshly.common.struct.platadmin.trade.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author oy
* @since 2020-11-16
*/
public abstract class TradeVO implements Serializable {

    @Data
    @ApiModel("TradeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("交易订单表(ID)")
        private String id;


        @ApiModelProperty("会员ID")
        private String userId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("来源类型:10:2C,20:2B,30:POS")
        private Integer sourceType;


        @ApiModelProperty("交易编号")
        private String tradeCode;


        @ApiModelProperty("订单状态:10:待支付,20:待发货,30:待收货,40:已完成,50:已取消")
        private Integer tradeState;


        @ApiModelProperty("商品总金额")
        private BigDecimal goodsAmount;


        @ApiModelProperty("优惠金额")
        private BigDecimal discountAmount;


        @ApiModelProperty("运费金额")
        private BigDecimal deliveryAmount;


        @ApiModelProperty("交易总金额")
        private BigDecimal tradeAmount;


        @ApiModelProperty("创建时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty("支付时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;


        @ApiModelProperty("收货时间")
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;


        @ApiModelProperty("支付类型")
        private Integer payType;


        @ApiModelProperty("配送类型")
        private Integer deliveryType;


        @ApiModelProperty("自提码")
        private String takeGoodsCode;


        @ApiModelProperty("自提码图片")
        private String takeGoodsQrcode;


        @ApiModelProperty("收货地址ID")
        private String recvAddresId;


        @ApiModelProperty("收货人")
        private String recvPersonName;


        @ApiModelProperty("收货人电话")
        private String recvPhone;


        @ApiModelProperty("收货地址全文本")
        private String recvFullAddres;


        @ApiModelProperty("是否超时取消")
        private Integer timeoutCancel;


        @ApiModelProperty("买家留言")
        private String buyerRemark;


        @ApiModelProperty("发货备注")
        private String deliveryRemark;


        @ApiModelProperty("隐藏订单:1:是")
        private Boolean isHide;




    }

    @Data
    @ApiModel("TradeVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @ApiModel("TradePayVO.ListVOExport")
    @Accessors(chain = true)
    public static class ListVOExport implements Serializable{

        @ApiModelProperty(value = "交易订单号(ID)",position =1)
        private String id;


        @ApiModelProperty(value = "会员名称",position = 2)
        private String userName;


        @ApiModelProperty(value = "店铺名称",position = 3)
        private String shopName;



        @ApiModelProperty(value = "交易编号",position = 4)
        private String tradeCode;


        @ApiModelProperty(value = "交易状态",position = 5)
        private String  tradeState;


        @ApiModelProperty(value = "商品总金额",position = 6)
        private BigDecimal goodsAmount;


        @ApiModelProperty(value = "优惠金额",position = 7)
        private BigDecimal discountAmount;


        @ApiModelProperty(value = "运费金额",position = 8)
        private BigDecimal deliveryAmount;


        @ApiModelProperty(value = "交易总金额",position = 9)
        private BigDecimal tradeAmount;


        @ApiModelProperty(value = "创建时间",position = 10)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;


        @ApiModelProperty(value = "支付时间",position = 11)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payTime;

        @ApiModelProperty(value = "支付截止时间",position = 12)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime payDeadline;


        @ApiModelProperty(value = "收货时间",position = 13)
        @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime recvTime;


        @ApiModelProperty(value = "支付类型",position = 14)
        private String  payType;


        @ApiModelProperty(value = "配送类型",position = 15)
        private String deliveryType;

        @ApiModelProperty(value = "物流单号",position = 16)
        private String logisticsNumber;

        @ApiModelProperty(value = "物流公司代码",position = 17)
        private String logisticsCompanyCode;

        @ApiModelProperty(value = "物流公司名称",position = 18)
        private String logisticsCompanyName;


        @ApiModelProperty(value = "自提码",position = 19)
        private String takeGoodsCode;


        @ApiModelProperty(value = "自提码图片",position = 20)
        private String takeGoodsQrcode;


        @ApiModelProperty(value = "收货人",position = 21)
        private String recvPersonName;


        @ApiModelProperty(value = "收货人电话",position = 22)
        private String recvPhone;


        @ApiModelProperty(value = "收货地址全文本",position = 23)
        private String recvFullAddres;


        @ApiModelProperty(value = "是否超时取消",position = 24)
        private String timeoutCancel;


        @ApiModelProperty(value = "买家留言",position = 25)
        private String buyerRemark;


    }


    @Data
    @ApiModel("TradeVO.TradeInfoVO")
    public static class TradeInfoVO implements Serializable{
        @ApiModelProperty("订单id")
        private String tradeId;

        @ApiModelProperty("订单编号")
        private String tradeCode;

        @ApiModelProperty("订单状态")
        private Integer tradeState;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("下单时间")
        private LocalDateTime cdate;

        @ApiModelProperty("发货时间")
        private LocalDateTime deliveryTime;

        @ApiModelProperty("货号")
        private String goodsNo;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("金额")
        private BigDecimal salePrice;

        @ApiModelProperty("总金额")
        private BigDecimal payAmount;

        @ApiModelProperty("卖家手动调整金额")
        private BigDecimal merchantAmount;
    }


    @Data
    @ApiModel("TradeVO.PayDatelistVO")
    public static class PayDatelistVO implements Serializable{

        @ApiModelProperty("新增订单金额")
        private BigDecimal addTradeAmount;

        @ApiModelProperty("新增订单数量")
        private Integer addTradeCount;

        @ApiModelProperty("平均单价")
        private BigDecimal avgAmount;

        @ApiModelProperty("已付款订单金额")
        private BigDecimal payTradeAmount;

        @ApiModelProperty("已付款订单数量")
        private Integer payTradeCount;

        @ApiModelProperty("已取消订单金额")
        private BigDecimal cancelTradeAmount;

        @ApiModelProperty("已取消订单数量")
        private Integer cancelTradeCount;

        @ApiModelProperty("订单集合")
        private List<PackDatelistVO> packDatelist;

        @ApiModelProperty("平均单价集合")
        private AvgAmountlistVO avgAmountlist;

        @ApiModelProperty("取消集合")
        private List<PackCancelDatelistVO> packCancelDatelist;

    }


    @Data
    @ApiModel("TradeVO.PackDatelistVO")
    public static class PackDatelistVO implements Serializable{

        @ApiModelProperty("时间")
        private LocalDateTime cdate;

        @ApiModelProperty("金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("数量")
        private Integer count;

    }

    @Data
    @ApiModel("TradeVO.AvgAmountlistVO")
    public static class AvgAmountlistVO implements Serializable{

        @ApiModelProperty("时间")
        private LocalDateTime cdate;

        @ApiModelProperty("平均单价")
        private BigDecimal avgAmount;

    }

    @Data
    @ApiModel("TradeVO.PackCancelDatelistVO")
    public static class PackCancelDatelistVO implements Serializable{

        @ApiModelProperty("时间")
        private LocalDateTime cdate;

        @ApiModelProperty("金额")
        private BigDecimal tradeAmount;

        @ApiModelProperty("数量")
        private Integer count;

    }


    @Data
    @ApiModel("TradeVO.OperationlistVO")
    public static class OperationlistVO implements Serializable{

        @ApiModelProperty("新增订单金额")
        private BigDecimal addTradeAmount;

        @ApiModelProperty("新增订单数量")
        private Integer addTradeCount;

        @ApiModelProperty("平均单价")
        private BigDecimal avgAmount;

        @ApiModelProperty("独立访问量UV")
        private BigDecimal independentUV;

        @ApiModelProperty("售后订单数")
        private Integer aftermarketCount;

        @ApiModelProperty("订单集合")
        private List<PackDatelistVO> packDatelist;

        @ApiModelProperty("平均单价集合")
        private AvgAmountlistVO avgAmountlist;

        @ApiModelProperty("平均单价集合2")
        private List<AvgAmountlistVO> avgAmountlist2;

        @ApiModelProperty("取消集合")
        private List<PackCancelDatelistVO> packCancelDatelist;

        @ApiModelProperty("订单完成数据集合")
        private PayTradeVO payTradeList;

        @ApiModelProperty("总订单金额集合")
        private PayTradeAmountVO payTradeAmountList;

        @ApiModelProperty("访问量数据")
        private VisitorDateVO visitorDateList;

    }


    @Data
    @ApiModel("TradeVO.PayTradeVO")
    public static class PayTradeVO implements Serializable{

        @ApiModelProperty("已完成订单")
        private Integer payTradeCount;

        @ApiModelProperty("未完成订单")
        private Integer payNotTradeCount;

    }

    @Data
    @ApiModel("TradeVO.PayTradeAmountVO")
    public static class PayTradeAmountVO implements Serializable{

        @ApiModelProperty("已付款订单金额")
        private BigDecimal payTradeAmountCount;

        @ApiModelProperty("未付款订单金额")
        private BigDecimal payNotTradeAmountCount;

    }

    @Data
    @ApiModel("TradeVO.VisitorDateVO")
    public static class VisitorDateVO implements Serializable{

        @ApiModelProperty("新增访问量数据")
        private Integer addVisitorCount;

        @ApiModelProperty("总访问数据")
        private Integer allVisitorCount;

    }

    @Data
    @ApiModel("TradeVO.SalesSummaryVO")
    public static class SalesSummaryVO implements Serializable{

        @ApiModelProperty("最近7天销售额")
        private Integer weekDate;

        @ApiModelProperty("最近30天销售额")
        private Integer MonthDate;

        @ApiModelProperty("最近90天销售额")
        private Integer NinetyDate;

        @ApiModelProperty("会员总数")
        private Integer UserCount;

        @ApiModelProperty("订单数据[10待支付 20代发货 30待收货 50已取消]")
        private List<packTradeStatesDate> packTradeStatesDate;

    }

    @Data
    @ApiModel("TradeVO.PerformanceVO")
    public static class PerformanceVO implements Serializable{

        @ApiModelProperty("当年销售额")
        private Integer count;

        @ApiModelProperty("集合")
        private List<PackPerformanceVO> packPerformanceVO;

    }

    @Data
    @ApiModel("TradeVO.PackPerformanceVO")
    public static class PackPerformanceVO implements Serializable{

        @ApiModelProperty("月份")
        private Integer cdate;

        @ApiModelProperty("销售额")
        private BigDecimal tradeAmount;

    }

    @Data
    @ApiModel("TradeVO.GoodsDateVO")
    public static class GoodsDateVO implements Serializable{

        @ApiModelProperty("汇总")
        private List<PackGoodsDateListVO> packGoodsDateListVO;

        @ApiModelProperty("集合")
        private List<PackGoodsDateVO> packGoodsDateVO;

    }

    @Data
    @ApiModel("TradeVO.PackGoodsDateVO")
    public static class PackGoodsDateVO implements Serializable{

        @ApiModelProperty("月份")
        private Integer cdate;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品图片")
        private String skuImg;

        @ApiModelProperty("销售额")
        private BigDecimal tradeAmount;

    }

    @Data
    @ApiModel("TradeVO.PackGoodsDateListVO")
    public static class PackGoodsDateListVO implements Serializable{

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("销售额")
        private BigDecimal tradeAmount;

    }

    @Data
    @ApiModel("TradeVO.ClientListVO")
    public static class ClientListVO implements Serializable{

        @ApiModelProperty("汇总数据")
        private List<PackClientVO> packClientVOS;

        @ApiModelProperty("集合")
        private List<PackClientListVO> packClientListVOS;

    }

    @Data
    @ApiModel("TradeVO.PackGoodsDateVO")
    public static class PackClientListVO implements Serializable{

        @ApiModelProperty("月份")
        private Integer cdate;

        @ApiModelProperty("客户姓名")
        private String userName;

        @ApiModelProperty("销售额")
        private BigDecimal tradeAmount;

    }

    @Data
    @ApiModel("TradeVO.PackClientVO")
    public static class PackClientVO implements Serializable{

        @ApiModelProperty("客户姓名")
        private String userName;

        @ApiModelProperty("销售额")
        private BigDecimal tradeAmount;

    }


    @Data
    @ApiModel("TradeVO.packTradeStatesDate")
    public static class packTradeStatesDate implements Serializable{

        @ApiModelProperty("订单状态")
        private Integer tradeState;

        @ApiModelProperty("订单数量")
        private String count;

    }
}
