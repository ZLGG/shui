package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.foundation.dto.SettingsDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Starry
 * @Date 15:26 2020/9/27
 */
public abstract class GoodsCategoryDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "商品类别id", hidden = true)
        private String id;

        @ApiModelProperty(value = "商品类别名称")
        private String gsCategoryName;

        @ApiModelProperty(value = "商品类别父id")
        private String parentId;

        @ApiModelProperty(value = "商品类别级别")
        private Integer gsCategoryLevel;

        @ApiModelProperty(value = "商品类别logo")
        private String gsCategoryLogo;

        @ApiModelProperty(value = "默认展示模式")
        private Integer showType;

        @ApiModelProperty("显示范围")
        private Integer usefiled;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }

    @Data
    public static class Level3ETO extends ETO{
        @ApiModelProperty(value = "商品类别服务费率(只有三级有)")
        private BigDecimal gsCategoryFee;

    }

    @Data
    public static class Level2ETO extends ETO{

    }

    @Data
    public static class Level1ETO extends ETO{
        @ApiModelProperty(value = "商品类别平台使用费(只有一级有)")
        private BigDecimal gsCategoryMoney;
    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private String id;
    }

    @Data
    @AllArgsConstructor
    public static class ApplyIdDTO extends BaseDTO {
        @ApiModelProperty(value = "申请id")
        private String applyId;
    }

    @Data
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private List<String> idList;
    }

    @Data
    @Accessors(chain = true)
    public static class IdxETO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别排序")
        private Integer idx;
    }

    @Data
    @Accessors(chain = true)
    public static class UseFiledETO extends BaseDTO {
        @ApiModelProperty(value = "商品类别id")
        private String id;

        @ApiModelProperty(value = "商品类别使用范围")
        private Integer useFiled;
    }

    @Data
    @ApiModel("GoodsCategoryDTO.RightsSetting")
    @Accessors(chain = true)
    public static class RightsSetting extends BaseDTO {

        @ApiModelProperty(value = "退货设置的天数")
        private Integer refundDays;

        @ApiModelProperty(value = "换货设置的天数")
        private Integer returnDays;




    }
}
