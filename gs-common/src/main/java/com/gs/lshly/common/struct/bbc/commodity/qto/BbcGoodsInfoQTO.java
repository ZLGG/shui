package com.gs.lshly.common.struct.bbc.commodity.qto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author Starry
 * @since 2020-10-23
 */
@SuppressWarnings("serial")
public abstract class BbcGoodsInfoQTO implements Serializable {
	
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

    }

	@EqualsAndHashCode(callSuper=false)
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

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsSearchListQTO")
    @Accessors(chain = true)
    public static class GoodsSearchListQTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("用户id")
        private String userId;

        @ApiModelProperty("可用积分")
        private Integer okIntegral;

        @ApiModelProperty(value = "搜索入口（0-电信商城 1-积分商城 2-积分商城-我能兑换）")
        @NotNull(message = "搜索入口字段不能为空")
        private Integer searchEntry;

        @ApiModelProperty(value = "排序条件字段 10=综合（我能兑换） 20=销量 30=价格 40=上新 50=in会员")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.OrderGoodsListQTO")
    @Accessors(chain = true)
    public static class OrderGoodsListQTO extends BaseQTO {

        @ApiModelProperty(value = "排序条件字段 10=销售 20=评价(或综合) 30=价格 40=积分 50=发布时间")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsListByCategoryQTO")
    @Accessors(chain = true)
    public static class GoodsListByCategoryQTO extends GoodsListQTO {

        @ApiModelProperty("商品分类")
        private String categoryLevel;

    }

	@EqualsAndHashCode(callSuper=false)
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

	@EqualsAndHashCode(callSuper=false)
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

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.ShopGoodsQTO")
    @Accessors(chain = true)
    public static class ShopGoodsQTO extends BaseQTO {

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsSkuQTO")
    @Accessors(chain = true)
    public static class GoodsSkuQTO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty(value = "规格值")
        private String specValues;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsSharingQTO")
    @Accessors(chain = true)
    public static class GoodsSharingQTO implements Serializable {

        @ApiModelProperty("用户敏感信息加密数据")
        private String encryptedData;

        @ApiModelProperty(value = "加密算法的初始向量")
        private String iv;

        @ApiModelProperty("code")
        private String code;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.ShopGoodsIdQTO")
    public static class ShopGoodsIdQTO implements Serializable {
	    @ApiModelProperty("店铺id")
        private String shopId;
    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.IntegralGoodsQTO")
    @Accessors(chain = true)
    public static class IntegralGoodsQTO extends BaseQTO {

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("用户id")
        @NotBlank(message = "userId不能为空")
        private String userId;

        @ApiModelProperty("可用积分")
        private Integer okIntegral;

        @ApiModelProperty("商品类目id")
        private String categoryId;

        @ApiModelProperty(value = "排序条件字段 10=我能兑换 20=in会员 30=销量 40=价格 50=上新")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;
    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsIdQTO")
    public static class GoodsIdQTO extends BaseDTO {
	    @ApiModelProperty("商品id")
        private String goodsId;
    }

    //-------------------------------内部服务-------------------------

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.SkuIdListQTO")
    @Accessors(chain = true)
    public static class SkuIdListQTO implements Serializable {

        @ApiModelProperty(value = "skuId列表")
        private List<String> skuIdList;


    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.ShopIdListQTO")
    @Accessors(chain = true)
    public static class ShopIdListQTO implements Serializable {

        @ApiModelProperty(value = "店铺id列表")
        private List<String> shopId;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.GoodsIdListQTO")
    @Accessors(chain = true)
    public static class GoodsIdListQTO implements Serializable {

        @ApiModelProperty(value = "商品id列表")
        private List<String> idList;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.TopicQTO")
    @Accessors(chain = true)
    public static class TopicQTO extends BaseDTO {
        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分专栏   60=电信国际]", hidden = true)
        private Integer subject;

        @ApiModelProperty(value = "10 20", hidden = true)
        private Integer terminal;
    }

    /**
     * IN会员查询专区
     *
     * @author yingjun
     * @date 2021年3月30日 下午6:44:11
     */
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcGoodsInfoQTO.InMemberGoodsQTO")
    @Accessors(chain = true)
    public static class InMemberGoodsQTO extends BaseQTO {
    	
    	@ApiModelProperty("in会员优惠券类型（20,30,50,99,200） 为空查询所有")
        private Integer inCouponType;

    }

	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.InSpecialAreaGoodsQTO")
    @Accessors(chain = true)
    public static class InSpecialAreaGoodsQTO extends BaseQTO {
        @ApiModelProperty("in会员优惠券类型（20,30,50,99,200）")
        @NotNull(message = "inCouponType不能为空")
        private Integer inCouponType;

        @ApiModelProperty(value = "排序条件字段 10=综合 20=销量 30=价格 40=上新")
        private Integer orderByProperties;

        @ApiModelProperty(value = "排序方式 10=升序 20=降序")
        private Integer orderByType;

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.EnjoyQTO")
    @Accessors(chain = true)
    public static class EnjoyQTO extends BaseQTO {

    }

    @Data
    @ApiModel("BbcGoodsInfoQTO.SearchHistoryQTO")
    @Accessors(chain = true)
    public static class SearchHistoryQTO implements Serializable{
        @ApiModelProperty("用户id")
        @NotBlank(message = "用户id不能为空")
        private String userId;

        @ApiModelProperty("搜索入口（0-电信商城，1-积分商城）")
        @NotNull(message = "搜索入口不能为空")
        private Integer searchEntry;
    }
    
	@EqualsAndHashCode(callSuper=false)
    @Data
    @ApiModel("BbcGoodsInfoQTO.SpecInfoByGoodsQTO")
    @Accessors(chain = true)
    public static class SpecInfoByGoodsQTO implements Serializable {

        @ApiModelProperty("商品id")
        private String goodsId;

    }
}
