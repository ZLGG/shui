package com.gs.lshly.common.struct.bbb.pc.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class PCBbbGoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("是否是扶贫推荐商品 0=否，1=是")
        private Integer recommendState;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoQTO.SearchByGoodsNameQTO")
    @Accessors(chain = true)
    public static class SearchByGoodsNameQTO extends BaseQTO {
        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoQTO.ShopNavigationIdQTO")
    @Accessors(chain = true)
    public static class ShopNavigationIdQTO extends BaseQTO {
        @ApiModelProperty("店铺自定义分类")
        private String shopNavigationId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty(value = "排序条件字段 10=销售 20=评价(或综合) 30=价格")
        private Integer orderByProperties;

        @ApiModelProperty(value = "按什么方式排序 10=升序 20=降序")
        private Integer orderByType;
    }

    @Data
    @ApiModel("PCBbbGoodsInfoQTO.GoodsSearchQTO")
    @Accessors(chain = true)
    public static class GoodsSearchQTO extends BaseQTO {
        @ApiModelProperty("二级类目id")
        private String level2CategoryId;

        @ApiModelProperty("三级类目id")
        private List<String> level3CategoryId;

        @ApiModelProperty("品牌id")
        private List<String> brandId;

        @ApiModelProperty("是否仅显示现货 10=显示 20=不显示")
        private Integer hasStock;

        @ApiModelProperty(value = "排序条件字段 10=销售 20=评价(或综合) 30=价格")
        private Integer orderByProperties;

        @ApiModelProperty("是否是积分商品")
        private Boolean isPointGood;

        @ApiModelProperty("出售类型（0普通，1活动）")
        private Integer saleType;

        @ApiModelProperty("是否是in会员礼品")
        private Boolean isInMemberGift;
    }


}
