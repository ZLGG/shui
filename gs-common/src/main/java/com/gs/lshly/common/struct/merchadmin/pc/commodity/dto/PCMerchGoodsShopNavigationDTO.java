package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-11-09
*/
public abstract class PCMerchGoodsShopNavigationDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsShopNavigationDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
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
    @ApiModel("PCMerchGoodsShopNavigationDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsShopNavigationDTO.IdListDTO")
    @AllArgsConstructor
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private List<String> idList;
    }

    @Data
    @ApiModel("PCMerchGoodsShopNavigationDTO.bindGoodsDTO")
    public static class BindGoodsDTO extends BaseDTO {
        @ApiModelProperty(value = "店铺自定义类目id")
        private String id;

        @ApiModelProperty(value = "商品id列表")
        private List<String> goodsIdList;
    }


}
