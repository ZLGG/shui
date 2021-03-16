package com.gs.lshly.common.struct.platadmin.stock.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author zhou
 * @date 17:39 2020/10/9
 */
public abstract class FreightChargeTemplateVO implements Serializable {

    @ApiModel("FreightChargeTemplateVO.DetailVO")
    @Data
    public static class DetailVO implements Serializable {
        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("运费模板名")
        private String name;

        @ApiModelProperty("快递公司ID")
        private String expressCompanyId;

//    /** 包邮吗 */
//    private Boolean freeflag;

        /**
         * 计价方式
         * 0-金额、1-数量、2-重量
         */
        @ApiModelProperty("ID")
        private Integer calculateWay;

        /** 店铺ID */
        @ApiModelProperty("ID")
        private String shopId;

        /** 模板是否启用 */
        @ApiModelProperty("ID")
        private Boolean enabled;

        @ApiModelProperty("ID")
        private LocalDateTime cdate;

        @ApiModelProperty("ID")
        private LocalDateTime udate;

        /**
         * 收费区域项list
         */
        @ApiModelProperty("ID")
        private Set<ChargeRegionPriceVO> chargeRegionPrices;

        /**
         * 免费区域项list
         */
        @ApiModelProperty("ID")
        private Set<ChargeRegionFreeVO> chargeRegionFrees;

        @ApiModel("FreightChargeTemplateVO.DetailVO.ChargeRegionFreeVO")
        @Data
        public static class ChargeRegionPriceVO implements Serializable {
            @ApiModelProperty("ID")
            private String id;

            @ApiModelProperty("计价方式：10-金额方式、20-数量方式、30-重量方式")
            private Integer type;

            @ApiModelProperty("地区ID数组字符串")
            private String regionIds;

            @ApiModelProperty("地区list")
            private Set<RegionVO.ListVO> regions;

            @ApiModelProperty("定义的价格项")
            private Set<PriceItemVO> priceItems;
        }

        @ApiModel("FreightChargeTemplateVO.DetailVO.ChargeRegionFreeVO")
        @Data
        public static class ChargeRegionFreeVO implements Serializable {
            @ApiModelProperty("ID")
            private String id;

            @ApiModelProperty("免邮条件类型：10-重量条件、20-件数条件、30-金额条件、40-金额+重量条件、50-金额+件数条件")
            private Integer conditionType;

            /** 地区ID数组字符串 */
            @ApiModelProperty("地区ID数组字符串")
            private String regionIds;

            /** 免邮的条件表达式 */
            @ApiModelProperty("免邮的条件表达式")
            private Set<ConditionExpressionVO> conditionExpressions;
        }

        @ApiModel("FreightChargeTemplateVO.DetailVO.PriceItemVO")
        @Data
        public static class PriceItemVO implements Serializable {

            @ApiModelProperty("ID")
            private String id;

            @ApiModelProperty("下限")
            private BigDecimal low;

            @ApiModelProperty("上限")
            private BigDecimal high;

            @ApiModelProperty("范围内费用（针对金额方式计价）")
            private BigDecimal cost;

            @ApiModelProperty("增量（针对重量、件数方式计价）")
            private Integer quantity;

            @ApiModelProperty("增加费用")
            private BigDecimal incr;
        }

        @ApiModel("FreightChargeTemplateVO.DetailVO.ConditionExpressionVO")
        @Data
        public static class ConditionExpressionVO implements Serializable {
            @ApiModelProperty("ID")
            private String id;

            @ApiModelProperty("条件名")
            private String name;

            @ApiModelProperty("条件值")
            private String value;

            @ApiModelProperty("条件符号")
            private String operator;
        }

    }

    @ApiModel("FreightChargeTemplateVO.ListVO")
    @Data
    public static class ListVO implements Serializable {

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("运费模板名")
        private String name;

        @ApiModelProperty("快递公司ID")
        private String expressCompanyId;

//    /** 包邮吗 */
//    private Boolean freeflag;

        @ApiModelProperty("计价方式：10-金额方式、20-数量方式、30-重量方式")
        private Integer calculateWay;

        /** 店铺ID */
        @ApiModelProperty("店铺ID")
        private String shopId;

        /** 模板是否启用 */
        @ApiModelProperty("模板是否启用")
        private Boolean enabled;

        private LocalDateTime cdate;

        private LocalDateTime udate;

        /**
         * 收费区域项list
         */
        @ApiModelProperty("收费区域项list")
        private Set<DetailVO.ChargeRegionPriceVO> chargeRegionPrices;


        public ListVO() {

        }

        public ListVO(String id, String name, String shopId, boolean enabled) {
            this.id = id;
            this.name = name;
            this.shopId = shopId;
            this.enabled = enabled;
        }
    }
}
