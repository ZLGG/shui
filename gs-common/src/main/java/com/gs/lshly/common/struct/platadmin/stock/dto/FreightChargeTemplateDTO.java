package com.gs.lshly.common.struct.platadmin.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 */
public abstract class FreightChargeTemplateDTO implements Serializable {

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty("ID")
        private String id;
    }

    @Data
    @AllArgsConstructor
    public static class ShopIdDTO extends BaseDTO {
        @ApiModelProperty("店铺ID")
        private String shopId;
    }


    @ApiModel("运费模板form表单")
    @Data
    public static class Form extends BaseDTO {

        @ApiModelProperty("运费模板名")
        private String name;

        @ApiModelProperty("快递公司")
        private String expressCompanyId;

//        @ApiModelProperty("包邮吗")
//        private boolean freeflag;

        @ApiModelProperty("计价方式：10-金额方式、20-数量方式、30-重量方式")
        private Integer calculateWay;

//        @ApiModelProperty("店铺ID")
//        private String shopId;

        @ApiModelProperty("模板是否启用")
        private Boolean enabled;

        @ApiModelProperty("每个地区的定价")
        private List<ChargeRegionPriceForm> regionPrices;

        @ApiModelProperty("每个地区的免价")
        private List<ChargeRegionFreeForm> regionFrees;

        /**
         * 地区的定价数据
         */
        @ApiModel("运费模板地区")
        @Data
        public static class ChargeRegionPriceForm {

            @ApiModelProperty("地区ID")
            private List<String> regionIds;

            @ApiModelProperty("价格项")
            private List<Element> elements;

            /**
             * 价格项
             */
            @ApiModel("运费模板价格项")
            @Data
            public static class Element {
                @ApiModelProperty("范围起始值")
                private BigDecimal low;

                @ApiModelProperty("范围结束值")
                private BigDecimal high;

                @ApiModelProperty("范围价格")
                private BigDecimal cost;

                @ApiModelProperty("数量增量")
                private Integer quantity;

                @ApiModelProperty("价格增量")
                private BigDecimal incr;
            }

        }

        /**
         * 地区的免价数据
         */
        @ApiModel("运费模板免费地区")
        @Data
        public static class ChargeRegionFreeForm {
            @ApiModelProperty("地区ID数组")
            private List<String> regionIds;

            @ApiModelProperty("条件类型：10-重量条件、20-件数条件、30-金额条件、40-金额+重量条件、50-金额+件数条件")
            private Integer conditionType;

            @ApiModelProperty("条件数据")
            private List<ConditionExpressionForm> expressions;

            /**
             * 运费模板免费条件
             */
            @ApiModel("运费模板免费条件")
            @Data
            public static class ConditionExpressionForm {
                @ApiModelProperty("条件名：amount、weight、number")
                private String name;

                @ApiModelProperty("条件值")
                private String value;

                @ApiModelProperty("条件符号：lt-小于、gt-大于、eq-等于")
                private String operator;

            }
        }

    }


}
