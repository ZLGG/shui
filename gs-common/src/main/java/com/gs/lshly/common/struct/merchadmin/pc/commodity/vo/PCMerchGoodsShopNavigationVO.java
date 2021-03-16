package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-11-09
*/
public abstract class PCMerchGoodsShopNavigationVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsShopNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商品ID")
        private String goodsId;


        @ApiModelProperty("店铺导航分类ID")
        private String shopNavigation;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;


        @ApiModelProperty("终端 10=2b 20=2c")
        private Integer terminal;


    }

    @Data
    @ApiModel("PCMerchGoodsShopNavigationVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
