package com.gs.lshly.common.struct.merchadmin.pc.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2021-02-23
*/
public abstract class PCMerchGoodsPosTemporaryDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "主键id",hidden = true)
        private String id;

        @ApiModelProperty("POS店编号")
        private String posCode;

        @ApiModelProperty("店铺商品spuId")
        private String spuId;

        @ApiModelProperty("商品规格名称")
        private String specName;

        @ApiModelProperty("商品规格值")
        private String specValue;

        @ApiModelProperty("店铺商品sku 69码")
        private String posSku69Code;

        @ApiModelProperty("商品sku名称")
        private String name;

        @ApiModelProperty("商品图片")
        private String images;

        @ApiModelProperty("商品价格")
        private BigDecimal price;

        @ApiModelProperty("库存变动流水号")
        private String stockChangeSerialNo;

        @ApiModelProperty("库存总量")
        private Integer totalStock;

        @ApiModelProperty("是否已发布 10=未发布 20=已发布")
        private Integer isRelease;

        @ApiModelProperty("当前时间毫秒数")
        private String timestamp;

        @ApiModelProperty("当前时间毫秒数")
        private String nonce;
    }

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "idlist列表")
        private List<String> idList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "主键id")
        private String id;
    }

    @Data
    @ApiModel("PCMerchGoodsPosTemporaryDTO.PosSpuIdDTO")
    @AllArgsConstructor
    public static class PosSpuIdDTO extends BaseDTO {

        @ApiModelProperty(value = "pos的spuID")
        private String spuId;
    }


}
