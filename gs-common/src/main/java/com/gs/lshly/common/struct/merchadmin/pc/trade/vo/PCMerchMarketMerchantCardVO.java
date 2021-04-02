package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class PCMerchMarketMerchantCardVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantCardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("优惠卷前缀")
        private String cardPrefix;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家优惠卷名称")
        private String cardName;


        @ApiModelProperty("描述")
        private String cardDescribe;

        //MerchantCardOperationEnum
        @ApiModelProperty("操作枚举类型[10=未审核 20=可领取 30=待生效 40=已取消 50=结束领取 60=生效中 70=待领取 80=已失效]")
        private String operation ;


        @ApiModelProperty("适用平台枚举类型[10=pc端 20=wap端 30=app端]")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;


        @ApiModelProperty("满多少")
        private BigDecimal toPrice;


        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;


        @ApiModelProperty("优惠卷发行数量")
        private Integer quantity;


        @ApiModelProperty("每人可领取数量")
        private Integer userGetMax;


        @ApiModelProperty("已领取总数")
        private Integer receivedTotal;


        @ApiModelProperty("已使用总数")
        private Integer usedTotal;


        @ApiModelProperty("可领时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getStartTime;


        @ApiModelProperty("可领时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getEndTime;


        @ApiModelProperty("有效时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validStartTime;


        @ApiModelProperty("有效时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validEndTime;

        @ApiModelProperty("提交审核标记")
        private Boolean isCommit;

        @ApiModelProperty("平台审核状态枚举类型[10=待审 20=通过 30=拒审]")
        private Integer state;


        @ApiModelProperty("取消优惠卷标记")
        private Boolean isCancel;


        @ApiModelProperty("拒审原因")
        private String revokeWhy;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantCardVO.DetailVO")
    public static class  DetailVO  extends ListVO{
        @ApiModelProperty("所属商家")
        private String shopName;
        //PlatformCardStatusEnum
        @ApiModelProperty("促销状态枚举类型[10=待审核 20=审核通过 30=已取消 40=未审核 50=审核拒绝]")
        private Integer promotionStatus;

        @ApiModelProperty("建卷时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime cdate;

    }
    /**
     * 平台--》优惠卷列表--》查看
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantCardVO.PlatformView")
    public static class PlatformView extends ListVO {
        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("创建时间")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime checkDate;

        @ApiModelProperty("商品信息")
        private List<PCMerchMarketMerchantCardVO.GoodsInfo> goodsInfos;

        @ApiModelProperty("所属商家")
        private List<PCMerchMarketMerchantCardVO.PlatformView.CheckVO> checkInfo;


        @Data
        @ApiModel("PCMerchMarketMerchantCardVO.PlatformView.CheckVO")
        @AllArgsConstructor
        @NoArgsConstructor
        @Accessors(chain = true)
        public static class  CheckVO  implements Serializable{
            @ApiModelProperty("审核时间")
            @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
            private LocalDateTime checkDate;

            @ApiModelProperty("审核状态")
            private Integer checkState;

            @ApiModelProperty("原因")
            private String remark;
        }
    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantCardVO.GoodsInfo")
    public static class GoodsInfo extends ListVO {
        @ApiModelProperty("SKUid")
        private String id;
        @ApiModelProperty("商品信息+规格名")
        private String goodsName;
        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

    }

    /**
     * 商家端优惠查看
     * */
    @Data
    @ApiModel("PCMerchMarketMerchantCardVO.View")
    public static class View implements Serializable {
        @ApiModelProperty("商家优惠卷名称")
        private String cardName;


        @ApiModelProperty("描述")
        private String cardDescribe;

        @ApiModelProperty("适用平台枚举类型[10=全平台 20=pc端 30=wap端 40=app端]")
        private String terminal;


        @ApiModelProperty("适用会员等级(1,2,3,4,5,6)")
        private String onUserLeve;

        @ApiModelProperty("已选择适用商品")
        private List<PCMerchMarketMerchantCardGoodsVO.View> goodsList;

        @ApiModelProperty("满多少")
        private BigDecimal toPrice;


        @ApiModelProperty("减多少")
        private BigDecimal cutPrice;


        @ApiModelProperty("优惠卷发行数量")
        private Integer quantity;


        @ApiModelProperty("每人可领取数量")
        private Integer userGetMax;


        @ApiModelProperty("已领取总数")
        private Integer receivedTotal;

        @ApiModelProperty("可领时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getStartTime;


        @ApiModelProperty("可领时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime getEndTime;


        @ApiModelProperty("有效时间段-开始")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validStartTime;


        @ApiModelProperty("有效时间段-结束")
        @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private LocalDateTime validEndTime;
    }
}
