package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class RSSaleFavItem extends RSEntity {
    @ApiModelProperty("优惠类型")
    private String favType;
    @ApiModelProperty("优惠金额（单位：元）")
    private BigDecimal favAmount;
    @ApiModelProperty("优惠原因类型")
    private String causeType;
    @ApiModelProperty("完整的优惠原因JSON数据")
    private String causeJson;
}