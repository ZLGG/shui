package com.gs.lshly.common.struct.bbc.commodity.dto;
import java.io.Serializable;
import java.math.BigDecimal;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.bbc.commodity.qto.BbcGoodsCategoryQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
* @author Starry
* @since 2020-10-23
*/
@SuppressWarnings("serial")
public abstract class BbcGoodsCategoryDTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
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
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品类别id")
        private String id;
    }
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryDTO.ListDTO")
    @Accessors(chain = true)
    public static class ListDTO extends BaseDTO {
        @ApiModelProperty("商品类别父id")
        private String parentId;
        @ApiModelProperty("查询所有子节点")
        private Boolean showAll = false;
    }


	@Data
	@EqualsAndHashCode(callSuper=false)
    @ApiModel(value = "BbcGoodsCategoryDTO.CtccDTO")
    @Accessors(chain = true)
    public static class CtccDTO  extends BaseDTO implements Serializable {

    	@ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分专栏   60=电信国际   70=电信产品]", hidden = true)
        private Integer subject;

        @ApiModelProperty(value = "10 20", hidden = true)
        private Integer terminal;
    }
	
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryQTO.ThirdListDTO")
    @Accessors(chain = true)
    public static class ThirdListDTO extends BaseDTO {
        @ApiModelProperty("商品类别父id")
        private String parentId;
    }
    
    
    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("BbcGoodsCategoryQTO.ParentIdDTO")
    @Accessors(chain = true)
    public static class ParentIdDTO extends BaseDTO {
        @ApiModelProperty("商品类别父id")
        private String parentId;
    }
}
