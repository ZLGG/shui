package com.gs.lshly.common.struct.common.stock;
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
* @author zzg
* @since 2020-10-24
*/
public abstract class CommonStockTemplateDTO implements Serializable {

    @Data
    @ApiModel("CommonStockTemplateDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "运费模版ID（修改时必填，否则会新增）", position = 2)
        private String id;

        @ApiModelProperty(value = "运费模板名称",position = 3)
        private String templateName;

        @ApiModelProperty(value = "计费类型[10=卖家承担 20=计重 30=计件 40=金额范围]",position = 4)
        private Integer templateType;

        @ApiModelProperty(value = "启用状态[10=启用 20=禁用]",position = 5)
        private Integer state;

        @ApiModelProperty(value = "单位计费",position = 6)
        private List<UnitDTO> unitDTOList;

        @ApiModelProperty(value = "单位计费包邮条件",position = 7)
        private List<FreeDTO> freeDTOList;

        @ApiModelProperty(value = "金额范围",position = 8)
        private List<QuotaDTO> quotaDTOList;

    }

    @Data
    @ApiModel("CommonStockTemplateDTO.UnitDTO")
    @Accessors(chain = true)
    public static class UnitDTO implements Serializable{

        @ApiModelProperty(value = "运费模板主ID", hidden = true, position = 2)
        private String templateId;

        @ApiModelProperty(value = "是否默认[10=是 20=否]",position = 3)
        private Integer isDefault;

        @ApiModelProperty(value = "首（计价单位数量）",position = 4)
        private Integer firstQuantity;

        @ApiModelProperty(value = "首费",position = 5)
        private BigDecimal firstPrice;

        @ApiModelProperty(value = "续（计价单位数量）",position = 6)
        private Integer increaseQuantity;

        @ApiModelProperty(value = "续费",position = 7)
        private BigDecimal increasePrice;

        @ApiModelProperty(value = "单位计费地区配置",position = 8)
        List<RegionDTO> regionDTOList;

    }



    @Data
    @ApiModel("CommonStockTemplateDTO.QuotaDTO")
    @Accessors(chain = true)
    public static class QuotaDTO implements Serializable{

        @ApiModelProperty(value = "运费模板主ID", hidden = true, position = 2)
        private String templateId;

        @ApiModelProperty(value = "是否默认[10=是 20=否]",position = 3)
        private Integer isDefault;

        @ApiModelProperty(value = "金额范围",position = 7)
        List<QuotaPriceDTO> priceDTOList = new ArrayList<>();

        @ApiModelProperty(value = "金额范围计费地区配置",position = 7)
        List<RegionDTO> regionDTOList;

    }

    @Data
    @ApiModel("CommonStockTemplateDTO.QuotaPriceDTO")
    @Accessors(chain = true)
    public static class QuotaPriceDTO implements Serializable{
        @ApiModelProperty(value = "范围小(以0开始)",position = 4)
        private BigDecimal minValue;

        @ApiModelProperty(value = "范围大(-1，表示无穷大)",position = 5)
        private BigDecimal maxValue;

        @ApiModelProperty(value = "金额",position = 6)
        private BigDecimal price;
    }


    @Data
    @ApiModel("CommonStockTemplateDTO.RegionSDTO")
    @Accessors(chain = true)
    public static class RegionDTO implements Serializable{

        @ApiModelProperty(value = "省ID",position = 1)
        private String id;

        @ApiModelProperty(value = "省",position = 2)
        private String province;

        @ApiModelProperty(value = "市数组",position = 3)
        private List<RegionCDTO> regionCDTO;
    }

    @Data
    @ApiModel("CommonStockTemplateDTO.RegionCDTO")
    @Accessors(chain = true)
    public static class RegionCDTO implements Serializable{

        @ApiModelProperty("市ID")
        private String id;

        @ApiModelProperty("市")
        private String city;

    }

    @Data
    @ApiModel("CommonStockTemplateDTO.FreeDTO")
    @Accessors(chain = true)
    public static class FreeDTO implements Serializable{

        @ApiModelProperty(value = "是否默认[10=是 20=否]",position = 2)
        private Integer isDefault;

        @ApiModelProperty(value = "包邮方式[10=单位数量 20=金额 30=组合(单位数量+金额]",position = 3)
        private Integer mathType;

        @ApiModelProperty(value = "单位数量值",position = 3)
        private Integer unitValue;

        @ApiModelProperty(value = "金额值",position = 4)
        private BigDecimal priceValue;

        @ApiModelProperty(value = "包邮条件地区配置",position = 5)
        List<RegionDTO> regionDTOList;
    }


    @Data
    @ApiModel("CommonStockTemplateDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "模板id")
        private String id;
    }


}
