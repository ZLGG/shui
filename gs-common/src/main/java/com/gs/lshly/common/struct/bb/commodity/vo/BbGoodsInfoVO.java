package com.gs.lshly.common.struct.bb.commodity.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月30日 下午6:32:10
 */
public abstract class BbGoodsInfoVO implements Serializable {

    @Data
    @ApiModel("BbGoodsInfoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品id")
        private String id;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("品牌id")
        private String brandId;


        @ApiModelProperty("类目id")
        private String categoryId;


        @ApiModelProperty("商品拓展规格id")
        private String specInfoId;


        @ApiModelProperty("商品拓展属性id")
        private String attributeInfoId;


        @ApiModelProperty("商品拓展参数id")
        private String extendParamsId;


        @ApiModelProperty("商品名称")
        private String goodsName;


        @ApiModelProperty("商品标题")
        private String goodsTitle;


        @ApiModelProperty("商品状态")
        private Integer goodsState;


        @ApiModelProperty("商品编号")
        private String goodsNo;


        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品原价")
        private BigDecimal oldPrice;


        @ApiModelProperty("商品成本价")
        private BigDecimal costPrice;

        @ApiModelProperty("商品阶梯价")
        private String stepPrice;

        @ApiModelProperty("商品重量")
        private BigDecimal goodsWeight;

        @ApiModelProperty("移动端商品描述")
        private String goodsH5Desc;

        @ApiModelProperty("电脑端商品描述")
        private String goodsPcDesc;

        @ApiModelProperty("商家条形码")
        private String goodsBarcode;


        @ApiModelProperty("商品有效期")
        private Integer goodsValidDays;


        @ApiModelProperty("商品图片组")
        private String goodsImage;


        @ApiModelProperty("是否是扶贫商品")
        private Integer isSuportPoorGoods;


        @ApiModelProperty("使用平台")
        private Integer usePlatform;

        @ApiModelProperty(value = "是否显示原价")
        private Integer isShowOldPrice;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty(value = "商品计价单位")
        private String goodsPriceUnit;

        @ApiModelProperty("操作人")
        private String operator;

        @ApiModelProperty(value = "发布时间")
        private LocalDateTime publishTime;

        @ApiModelProperty("积分价格")
        private Double pointPrice;

        @ApiModelProperty("办理备注")
        private String remarks;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;

        @ApiModelProperty("in会员积分价格")
        private Double inMemberPointPrice;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("信天游产品号")
        private Integer thirdProductId;

        @ApiModelProperty("兑换类型（0实物,1虚拟）")
        private Integer exchangeType;

    }

}
