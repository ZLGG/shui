package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-02-23
*/
public abstract class PCMerchGoodsPosTemporaryVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;


        @ApiModelProperty(value = "POS店编号",position = 1)

        private String posCode;


        @ApiModelProperty(value = "pos店铺商品spuId",position = 2)
        private String spuId;

        @ApiModelProperty(value = "商品规格名称",position = 3)
        private String specName;

        @ApiModelProperty(value = "商品规格值",position = 4)
        private String specValue;


        @ApiModelProperty(value = "店铺商品sku 69码",position = 5)
        private String posSku69Code;


        @ApiModelProperty(value = "商品sku名称",position = 6)
        private String name;


        @ApiModelProperty(value = "商品图片",position = 7,hidden = true)
        private String images;


        @ApiModelProperty(value = "商品价格",position = 7)
        private BigDecimal price;


        @ApiModelProperty(value = "库存变动流水号",position = 8)
        private String stockChangeSerialNo;


        @ApiModelProperty(value = "库存总量",position = 9)
        private Integer totalStock;

        @ApiModelProperty("是否已发布 10=未发布 20=已发布")
        private Integer isRelease;


    }

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryVO.ImageVO")
    public static class ImageVO implements Serializable {

        private String imgSrc;

    }
}
