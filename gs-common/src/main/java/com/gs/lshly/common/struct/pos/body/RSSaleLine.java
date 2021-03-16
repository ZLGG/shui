package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class RSSaleLine extends RSEntity{
    private static final long serialVersionUID=7665403340472827924L;
    @ApiModelProperty("商品uuid")
    private String shopSku;
    @ApiModelProperty("商品条码")
    private String skuBarcode;
    @ApiModelProperty("商品名称")
    private String skuName;
    @ApiModelProperty("商品单位")
    private String skuMunit;
    @ApiModelProperty("商品分类编码")
    private String skuCategoryCode;
    @ApiModelProperty("商品分类名称")
    private String skuCategoryName;
    @ApiModelProperty("商品原售价")
    private BigDecimal skuSalePrice;
    @ApiModelProperty("商品成本价")
    private BigDecimal skuCostPrice;
    @ApiModelProperty("当前售价")
    private BigDecimal price;
    @ApiModelProperty("商品数量")
    private BigDecimal qty;
    @ApiModelProperty("小计")
    private BigDecimal amount;
    @ApiModelProperty("导购员")
    private UCN guide;
    @ApiModelProperty("商品行优惠明细, 含单品优惠+整单分摊优惠项")
    private List<RSSaleFavItem> saleLineFavItems = new ArrayList<>();
}