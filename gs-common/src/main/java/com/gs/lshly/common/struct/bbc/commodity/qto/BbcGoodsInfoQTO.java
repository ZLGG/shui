package com.gs.lshly.common.struct.bbc.commodity.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbb.pc.commodity.qto.PCBbbGoodsCategoryQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class BbcGoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsListQTO")
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
    @ApiModel("BbcGoodsInfoQTO.OrderGoodsListQTO")
    @Accessors(chain = true)
    public static class OrderGoodsListQTO extends BaseQTO {

        @ApiModelProperty(value = "排序条件字段 10=销售 20=评价(或综合) 30=价格 40=积分 50=发布时间")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsListByCategoryQTO")
    @Accessors(chain = true)
    public static class GoodsListByCategoryQTO extends GoodsListQTO {

        @ApiModelProperty("商品分类")
        private String categoryLevel;

    }


    @Data
    @ApiModel("BbcGoodsInfoQTO.MerchantGoodsQTO")
    @Accessors(chain = true)
    public static class MerchantGoodsQTO extends GoodsListQTO {

        @ApiModelProperty(value = "店铺id")
        private String shopId;

        @ApiModelProperty("商品店铺类目id")
        private String shopNavigationId;
        
        @ApiModelProperty("是否全部显示积分商品 0 否；1 是")
        private Integer isPointGood;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.MerchantShopGoodsQTO")
    @Accessors(chain = true)
    public static class MerchantShopGoodsQTO extends GoodsListQTO {

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty(value = "店铺id")
        private String shopId;

        @ApiModelProperty(value = "店铺自定义类目id")
        private String shopNavigationId;


    }


    @Data
    @ApiModel("BbcGoodsInfoQTO.ShopGoodsQTO")
    @Accessors(chain = true)
    public static class ShopGoodsQTO extends BaseQTO {

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsSkuQTO")
    @Accessors(chain = true)
    public static class GoodsSkuQTO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty(value = "规格值")
        private String specValues;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsSharingQTO")
    @Accessors(chain = true)
    public static class GoodsSharingQTO implements Serializable {

        @ApiModelProperty("用户敏感信息加密数据")
        private String encryptedData;

        @ApiModelProperty(value = "加密算法的初始向量")
        private String iv;

        @ApiModelProperty("code")
        private  String code;

    }

    //-------------------------------内部服务-------------------------

    @Data
    @ApiModel("BbcGoodsInfoQTO.SkuIdListQTO")
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
    @ApiModel("BbcGoodsInfoQTO.TopicQTO")
    @Accessors(chain = true)
    public static class TopicQTO extends BaseDTO {
    	@ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50]",hidden=true)
        private Integer subject;
    	
    	@ApiModelProperty(value="10 20",hidden=true)
        private Integer terminal;
    }
    
    /**
     * IN会员查询专区
     *
     * 
     * @author yingjun
     * @date 2021年3月30日 下午6:44:11
     */
    @Data
    @ApiModel("BbcGoodsInfoQTO.InMemberGoodsQTO")
    @Accessors(chain = true)
    public static class InMemberGoodsQTO extends BaseQTO {

    }
}
