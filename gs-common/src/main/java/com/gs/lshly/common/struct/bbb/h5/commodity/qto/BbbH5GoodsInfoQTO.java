package com.gs.lshly.common.struct.bbb.h5.commodity.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class BbbH5GoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.GoodsListQTO")
    @Accessors(chain = true)
    public static class GoodsListQTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty(value = "排序条件字段 10=销售 20=评价(或综合) 30=价格 40=积分 50=发布时间")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

    }


    @Data
    @ApiModel("BbbH5GoodsInfoQTO.GoodsListByCategoryQTO")
    @Accessors(chain = true)
    public static class GoodsListByCategoryQTO extends GoodsListQTO {

        @ApiModelProperty("商品三级分类")
        private String categoryLevel;

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }


    @Data
    @ApiModel("BbbH5GoodsInfoQTO.MerchantShopGoodsQTO")
    @Accessors(chain = true)
    public static class MerchantShopGoodsQTO extends GoodsListQTO {

        @ApiModelProperty(value = "店铺id")
        private String shopId;

        @ApiModelProperty(value = "店铺自定义类目id")
        private String shopNavigationId;


    }


    @Data
    @ApiModel("BbbH5GoodsInfoQTO.ShopGoodsQTO")
    @Accessors(chain = true)
    public static class ShopGoodsQTO extends BaseQTO {

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.GoodsSkuQTO")
    @Accessors(chain = true)
    public static class GoodsSkuQTO extends BaseDTO {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty(value = "规格值")
        private String specValues;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.QuickOrderQTO")
    @Accessors(chain = true)
    public static class QuickOrderQTO extends BaseQTO {

        @ApiModelProperty("一级类目id")
        private String level1CategoryId;

    }

    //-------------------------------内部服务-------------------------

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.SkuIdListQTO")
    @Accessors(chain = true)
    public static class SkuIdListQTO implements Serializable {

        @ApiModelProperty(value = "skuId列表")
       private List<String> skuIdList;


    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.ShopIdListQTO")
    @Accessors(chain = true)
    public static class ShopIdListQTO implements Serializable {

        @ApiModelProperty(value = "店铺id列表")
        private List<String> shopId;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsIdListQTO")
    @Accessors(chain = true)
    public static class GoodsIdListQTO implements Serializable {

        @ApiModelProperty(value = "商品id列表")
        private List<String> idList;

    }

    @Data
    @ApiModel("BbbH5GoodsInfoQTO.InSpecialAreaGoodsQTO")
    @Accessors(chain = true)
    public static class InSpecialAreaGoodsQTO extends BaseQTO {
        @ApiModelProperty("in会员优惠券类型（20,30,50,99,200）")
        @NotNull(message = "inCouponType不能为空")
        private Integer inCouponType;
    }

}
