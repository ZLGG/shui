package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.gs.lshly.common.struct.platadmin.trade.vo.MarketPtSeckillVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 上午10:32:05
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillMerchantVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("秒杀ID")
        private String seckillId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("标签")
        private String label;


        @ApiModelProperty("描述")
        private String seckillDescribe;

        @ApiModelProperty("状态[10=审核 20=未审核 30=审核驳回]")
        private String  state;

        @ApiModelProperty("审核驳回原因")
        private String reasonsForRejection;

        @ApiModelProperty("报名开始时间")
        private LocalDateTime signStartTime;


        @ApiModelProperty("报名结束时间")
        private LocalDateTime signEndTime;


        @ApiModelProperty("秒杀上线时间")
        private LocalDateTime onlineStartTime;


        @ApiModelProperty("开售开始时间")
        private LocalDateTime seckillStartTime;


        @ApiModelProperty("开售结束时间")
        private LocalDateTime seckillEndTime;


        @ApiModelProperty("会员限购数量上限")
        private Integer userBuyMax;


        @ApiModelProperty("店铺参加商品数上限")
        private Integer shopGoodsMax;


        @ApiModelProperty("秒杀封面图")
        private String coverImage;


        @ApiModelProperty("开销提醒提前分钟数")
        private Integer smsBefore;


        @ApiModelProperty("是否短信提醒[10=是 20=否]")
        private Integer smsIsTell;




    }

    @Data
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.MerchantSeckill")
    public static class MyMerchantSeckill implements Serializable {
        @ApiModelProperty("秒杀记录id")
        private String id;


        @ApiModelProperty("秒杀名称")
        private String name;


        @ApiModelProperty("秒杀类型")
        private String label;

        @ApiModelProperty("报名时间")
        private String signTime;


        @ApiModelProperty("秒杀时间时间")
        private String seckillTime;

        @ApiModelProperty("状态[10=审核 20=未审核]")
        private String state;

    }

    /**
     * 平台秒杀审核列表
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.platformCheck")
    public static class platformCheck implements Serializable {
        @ApiModelProperty("商家秒杀记录ID")
        private String id;


        @ApiModelProperty("操作[10=审核通过 20=审核 30=审核驳回 40=无法审核](平台端无视)")
        private String operation;


        @ApiModelProperty("店铺名（店铺类型）")
        private String shopName;

        @ApiModelProperty("秒杀名")
        private String seckillName;


        @ApiModelProperty("状态[10=审核通过 20=待审核 30=审核被拒绝]")
        private String check;

        @ApiModelProperty("有效状态[10=是 20=否]")
        private String state;


        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udata;

        /**
         * 报名开始时间
         */
        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signStartTime;

        /**
         * 报名结束时间
         */
        @ApiModelProperty("报名更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime signEndTime;
    }
    /**
     * 平台秒杀审核列表
     * */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.platformCheckView")
    public static class platformCheckView implements Serializable {
        @ApiModelProperty("秒杀规则")
        private MarketPtSeckillVO.CheckSeckill seckill;


        @ApiModelProperty("店铺信息")
        private PCMerchMarketPtSeckillMerchantVO.platformCheck platformInfo;


        @ApiModelProperty("商品信息")
        private List<PCMerchMarketPtSeckillMerchantVO.platformCheckGoodsInfo> goodsInfoList;

    }
    /**
     * 商品信息
     * */
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.platformCheckGoodsInfo")
    public static class platformCheckGoodsInfo implements Serializable {
        @ApiModelProperty("商品ID")
        private String goodsId;
        @ApiModelProperty("商品编号")
        private String goodsNo;


        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品秒杀价")
        private BigDecimal seckillPrice;

        @ApiModelProperty("Sku秒杀价枚举类型[10=SKU促销价 20=此商品无sku值]")
        private PCMerchMarketPtSeckillMerchantVO.skuseckillPriceInfo skuseckillPriceInfos;
        //seckillSignEnum
        @ApiModelProperty("审核状态枚举类型[10=已审核 20=待审核]")
        private Integer checkState;
    }
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("PCMerchMarketPtSeckillMerchantVO.skuseckillPriceInfo")
    public static class skuseckillPriceInfo implements Serializable {
        @ApiModelProperty("Sku秒杀价枚举类型[10=SKU促销价 20=此商品无sku值]")
        private Integer skuSeckillPrice;
        @ApiModelProperty("商品SKUid")
        private List<String> id;

    }

}
