package com.gs.lshly.common.struct.common.stock;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author lxus
* @since 2020-10-24
*/
public abstract class CommonStockTemplateVO implements Serializable {

    @Data
    @ApiModel("CommonStockTemplateVO.IdNameVO")
    @Accessors(chain = true)
    public static class IdNameVO  implements  Serializable {
        @ApiModelProperty(value = "运费模版ID",position = 2)
        private String templateId;

        @ApiModelProperty(value = "运费模板名称",position = 3)
        private String templateName;
    }

    @Data
    @ApiModel("CommonStockTemplateVO.ListDetailVO")
    @Accessors(chain = true)
    public static class ListDetailVO  implements  Serializable {

        @ApiModelProperty(value = "运费模版ID",position = 2)
        private String id;

        @ApiModelProperty(value = "运费模板名称",position = 3)
        private String templateName;

        @ApiModelProperty(value = "计费类型[10=卖家承担 20=计重 30=计件 40=金额范围]",position = 4)
        private Integer templateType;

        @ApiModelProperty(value = "启用状态[10=启用 20=禁用]",position = 5)
        private Integer state;


        @ApiModelProperty(value = "单位计费",position = 6)
        private List<UnitVO> unitVOList = new ArrayList<>();

        @ApiModelProperty(value = "单位计费包邮条件",position = 7)
        private List<FreeVO> freeVOList = new ArrayList<>();

        @ApiModelProperty(value = "金额范围",position = 8)
        private List<QuotaVO> quotaVOList = new ArrayList<>();

        @ApiModelProperty(value = "更新时间",position = 9, example = "2020-11-15 18:02:16")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("CommonStockTemplateVO.UnitVO")
    @Accessors(chain = true)
    public static class UnitVO implements Serializable{

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "运费模板主ID",position = 2)
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

        @ApiModelProperty(value = "单位计费地区配置", position = 8)
        List<RegionVO> regionVOList = new ArrayList<>();

        @ApiModelProperty(value = "单位计费地区-字符串", position = 8)
        String address;

    }


    @Data
    @ApiModel("CommonStockTemplateVO.QuotaVO")
    @Accessors(chain = true)
    public static class QuotaVO implements Serializable{

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "运费模板主ID",position = 2)
        private String templateId;

        @ApiModelProperty(value = "是否默认[10=是 20=否]",position = 3)
        private Integer isDefault;

        @ApiModelProperty(value = "金额范围",position = 7)
        List<QuotaPriceVO> priceVOList = new ArrayList<>();

        @ApiModelProperty(value = "金额范围计费地区配置",position = 7)
        List<RegionVO> regionVOList = new ArrayList<>();

        @ApiModelProperty(value = "单位计费地区-字符串", position = 8)
        String address;

    }

    @Data
    @ApiModel("CommonStockTemplateVO.QuotaPriceVO")
    @Accessors(chain = true)
    public static class QuotaPriceVO implements Serializable{
        @ApiModelProperty(value = "范围小(以0开始)",position = 4)
        private BigDecimal minValue;

        @ApiModelProperty(value = "范围大(-1，表示无穷大)",position = 5)
        private BigDecimal maxValue;

        @ApiModelProperty(value = "金额",position = 6)
        private BigDecimal price;
    }

    @Data
    @ApiModel("CommonStockTemplateVO.FreeDTO")
    @Accessors(chain = true)
    public static class FreeVO implements Serializable{

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty(value = "是否默认[10=是 20=否]",position = 2)
        private Integer isDefault;

        @ApiModelProperty(value = "包邮方式[10=单位数量 20=金额 30=组合(单位数量+金额]",position = 3)
        private Integer mathType;

        @ApiModelProperty(value = "单位数量值",position = 3)
        private Integer unitValue;

        @ApiModelProperty(value = "金额值",position = 4)
        private BigDecimal priceValue;

        @ApiModelProperty(value = "包邮条件地区配置",position = 5)
        List<RegionVO> regionVOList = new ArrayList<>();

        @ApiModelProperty(value = "单位计费地区-字符串", position = 8)
        String address;
    }

    @Data
    @ApiModel("CommonStockTemplateVO.RegionSDTO")
    @Accessors(chain = true)
    public static class RegionVO implements Serializable{

        @ApiModelProperty(value = "省ID",position = 1)
        private String id;

        @ApiModelProperty(value = "省",position = 2)
        private String province;

        @ApiModelProperty(value = "市数组",position = 3)
        private List<RegionCVO> regionCVO = new ArrayList<>();
    }


    @Data
    @ApiModel("CommonStockTemplateVO.RegionCVO")
    @Accessors(chain = true)
    public static class RegionCVO implements Serializable{

        @ApiModelProperty("市ID")
        private String id;

        @ApiModelProperty("市")
        private String city;

    }




    @Data
    @ApiModel("CommonStockTemplateVO.TemplateIdAndSkuPriceVO")
    @Accessors(chain = true)
    public static class TemplateIdAndSkuPriceVO implements Serializable{

        @ApiModelProperty("运费模板id")
        private String templateId;

        @ApiModelProperty("sku集合")
        List<SkuAmountAndPriceVO> skus = new ArrayList<>();

    }

    @Data
    @ApiModel("CommonStockTemplateVO.TemplateIdAndSkuPriceVO")
    @Accessors(chain = true)
    public static class SkuAmountAndPriceVO implements Serializable{

        @ApiModelProperty("skuId")
        private String skuId;

        @ApiModelProperty("sku数量")
        private BigDecimal amount;

        @ApiModelProperty("sku重量")
        private BigDecimal weight;

        @ApiModelProperty("sku价格")
        private BigDecimal price;

    }




}
