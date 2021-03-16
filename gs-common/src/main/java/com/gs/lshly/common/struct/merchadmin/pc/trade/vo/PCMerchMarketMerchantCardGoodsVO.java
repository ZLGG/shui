package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchGoodsInfoVO;
import com.gs.lshly.common.struct.merchadmin.pc.commodity.vo.PCMerchSkuGoodInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zdf
* @since 2020-12-04
*/
public abstract class PCMerchMarketMerchantCardGoodsVO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家优惠卷ID")
        private String cardId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("SPU_ID(商品ID)")
        private String goodsId;


        @ApiModelProperty("SKU_ID")
        private String skuId;




    }

    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
    @Data
    @Accessors(chain = true)
    @ApiModel("PCMerchMarketMerchantCardGoodsVO.View")
    public static class View implements Serializable {

        @ApiModelProperty("商品ID")
        private String goodsId;
        @ApiModelProperty("图片URL")
        private String imageUrl;

        @ApiModelProperty("商品名字")
        private String goodsName;

        @ApiModelProperty("商品价格")
        private BigDecimal goodsPrice;

        @ApiModelProperty("查看SKU")
        private List<PCMerchSkuGoodInfoVO.ListVO> viewSKU;
    }
    @Data
    @ApiModel("PCMerchMarketMerchantCardGoodsVO.SkuView")
    public static class SkuView implements Serializable {
        @ApiModelProperty("sku图片URL")
        private String skuImageUrl;

        @ApiModelProperty("规格名字")
        private String typeCode;

        @ApiModelProperty("sku商品价格")
        private String skuGoodsPrice;


    }

}
