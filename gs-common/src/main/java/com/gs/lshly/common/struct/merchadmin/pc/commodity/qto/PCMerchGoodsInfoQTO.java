package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author Starry
* @since 2020-10-08
*/
public abstract class PCMerchGoodsInfoQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsInfoQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("店铺Id")
        private String shopId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品状态 10:待上架 20:已上架 30:审核中 40:已审核 50:草稿箱")
        private Integer goodsState;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("使用平台")
        private Integer usePlatform;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoQTO.GoodsActiveQTO")
    @Accessors(chain = true)
    public static class GoodsActiveQTO extends BaseQTO {
        @ApiModelProperty("分类id")
        private String categoryId;

        @ApiModelProperty("商品名称")
        private String goodsName;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("品牌名称")
        private String brandName;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoQTO.PageGoodsInfoQTO")
    public static class GoodsInfoParamsQTO extends  QTO{

        @ApiModelProperty("店铺自定义分类id")
        private String shopNavigation;

        @ApiModelProperty("模板id")
        private String templateId;

        @ApiModelProperty("商品价格1")
        private BigDecimal salePrice1;

        @ApiModelProperty("商品价格2")
        private BigDecimal salePrice2;

        @ApiModelProperty("申请类型 1:新增商品 2:更新商品")
        private Integer applyType;

        @ApiModelProperty("审核结果 1:通过 2:不通过")
        private Integer aduitType;

        @ApiModelProperty("商品id")
        private String id;
    }


    @Data
    @ApiModel("PCMerchGoodsInfoQTO.IdListQTO")
    public static class IdListQTO extends BaseQTO{

        @ApiModelProperty("商品id列表")
        private List<String> idList;

    }


    @Data
    @ApiModel("PCMerchGoodsInfoQTO.ShopFloorQTO")
    public static class ShopFloorQTO extends  BaseQTO{
        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("商品编号")
        private String goodsNo;

        @ApiModelProperty("商品标题")
        private String goodsName;

        @ApiModelProperty("终端")
        private Integer usePlatform;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoQTO.ShopNavigationQTO")
    public static class ShopNavigationQTO extends  BaseQTO{

        @ApiModelProperty("店铺类目id")
        private String shopNavigationId;

        @ApiModelProperty("终端")
        private Integer usePlatform;

    }

    @Data
    @ApiModel("PCMerchGoodsInfoQTO.usePlatformQTO")
    public static class usePlatformQTO extends  BaseQTO{

        @ApiModelProperty("终端")
        private Integer usePlatform;

    }
}
