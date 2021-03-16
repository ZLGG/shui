package com.gs.lshly.common.struct.bbb.h5.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* @author Starry
* @since 2020-10-23
*/
public abstract class BbbH5GoodsCategoryDTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品类别id",hidden = true)
        private String id;

        @ApiModelProperty("商品类别名称")
        private String gsCategoryName;

        @ApiModelProperty("商品类别父id")
        private String parentId;

        @ApiModelProperty("商品类别级别")
        private Integer gsCategoryLevel;

        @ApiModelProperty("商品类别logo")
        private String gsCategoryLogo;

        @ApiModelProperty("商品类别服务费率(只有三级有)")
        private BigDecimal gsCategoryFee;

        @ApiModelProperty("商品类别平台使用费(只有一级有)")
        private BigDecimal gsCategoryMoney;

        @ApiModelProperty("默认展示模板")
        private Integer showType;

        @ApiModelProperty("商品类别显示区域")
        private Integer useFiled;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("BbcGoodsCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品类别id")
        private String id;
    }


}
