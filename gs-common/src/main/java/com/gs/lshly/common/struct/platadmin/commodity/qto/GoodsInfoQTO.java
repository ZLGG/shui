package com.gs.lshly.common.struct.platadmin.commodity.qto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Starry
 * @Date 16:11 2020/10/14
 */
public abstract class GoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("GoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("所属店铺")
        private String shopName;

        @ApiModelProperty("商品标题")
        private String goodsName;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品类目")
        private String goodsCategory;

        @ApiModelProperty("商品品牌")
        private String goodsBrand;

        @ApiModelProperty("店铺类型")
        private Integer shopType;

        @ApiModelProperty("商品状态")
        private Integer goodState;

        @ApiModelProperty(value = "单品或者多规格商品（10 = 单品，20=多规格）")
        private Integer isSingle;

        @ApiModelProperty(value = "商品子标题")
        private String goodsTitle;

        @ApiModelProperty("商品售价")
        private BigDecimal salePrice;

        @ApiModelProperty("商品市场价")
        private BigDecimal oldPrice;

        @ApiModelProperty("商品成本价")
        private BigDecimal costPrice;

        @ApiModelProperty("商品售价2")
        private BigDecimal salePrice2;

        @ApiModelProperty("商品市场价2")
        private BigDecimal oldPrice2;

        @ApiModelProperty("商品成本价2")
        private BigDecimal costPrice2;

        @ApiModelProperty("商品积分价1")
        private BigDecimal pointPrice1;

        @ApiModelProperty("商品积分价2")
        private BigDecimal pointPrice2;

        @ApiModelProperty("标签Id")
        private String labelId;

        @ApiModelProperty("计价单位")
        private String goodsPriceUnit;

/*        @ApiModelProperty("是否是in会员商品(0:不是,1:是)")
        private Integer isInMemberGift;*/

        @ApiModelProperty("商品分区[(10:电信专区,20:in会员专区,30:电信国际,40:秒杀专区,50:扶贫专区]")
        private Integer goodsPlace;
    }

    @Data
    @ApiModel("GoodsInfoQTO.ShopFloorQTO")
    public static class ShopFloorQTO extends BaseQTO {

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品标题")
        private String goodsName;

        @ApiModelProperty("终端")
        private Integer usePlatform;

    }

    @Data
    @ApiModel("GoodsInfoQTO.FupinFloorQTO")
    public static class FupinFloorQTO extends BaseQTO {

        @ApiModelProperty(value = "商品Id", hidden = true)
        private List<String> goodsId;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品标题")
        private String goodsName;

        @ApiModelProperty("终端")
        private Integer usePlatform;

    }

    @Data
    @ApiModel("GoodsInfoQTO.CategoryIdQTO")
    public static class CategoryIdQTO extends BaseQTO {

        @ApiModelProperty("类目id")
        private String categoryId;

        @ApiModelProperty("类型 不传将无法查到数据")
        private Integer useFiled = 0;
    }

    @Data
    @ApiModel("GoodsInfoQTO.CategoryGoodsQTO")
    public static class CategoryGoodsQTO extends BaseQTO {

        @ApiModelProperty("类目级别(1:一级类目，3:三级类目)")
        private Integer level;

        @ApiModelProperty("类目id")
        @NotBlank(message = "类目id不能为空")
        private String categoryId;

        @ApiModelProperty(value = "排序条件字段 10=综合 20=销量 30=价格 40=上新 ")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

        @ApiModelProperty(value = "类型 不传将无法查到数据",hidden = true)
        @JsonIgnore
        private Integer useFiled = 0;
    }

}