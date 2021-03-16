package com.gs.lshly.common.struct.platadmin.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-14
*/
public abstract class ShopTypeDictDTO implements Serializable {

    @Data
    @ApiModel("ShopTypeDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺类型名[品牌旗舰店 品牌专卖店 类目专营店 运营商自营 多品类通用型")
        private String typeName;

        @ApiModelProperty("类型描述")
        private String typeDesc;

        @ApiModelProperty("状态[10=可用 20=禁用]")
        private Integer state;

        @ApiModelProperty("是否排它[10=否 20=是]")
        private Integer isMutex;

        @ApiModelProperty("商品数量上限")
        private Integer goodsMax;

        @ApiModelProperty("商家保证金")
        private BigDecimal bail;

        @ApiModelProperty("商家保证金告警")
        private BigDecimal bailDown;

        @ApiModelProperty("店铺名后缀")
        private String suffixName;
    }

    @Data
    @ApiModel("ShopTypeDictDTO.IdDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("ShopTypeDictDTO.ShopTypeDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ShopTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺类型")
        private Integer shopType;
    }

}
