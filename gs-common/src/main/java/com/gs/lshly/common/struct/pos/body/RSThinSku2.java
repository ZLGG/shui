package com.gs.lshly.common.struct.pos.body;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;
@Data
@Accessors(chain = true)
public class RSThinSku2 {
    @ApiModelProperty(value = "商品id", notes = "12312365-sfs-fdsd45f-fdsdfdf")
    private String id;
    @ApiModelProperty(value = "商品条码", example = "6905623235623")
    private String barcode;
    @ApiModelProperty(value = "商品名称", example = "西湖龙井 100g")
    private String name;
    @ApiModelProperty(value = "计量单位", example = "罐")
    private String munit;
    @ApiModelProperty(value = "规格", example = "100g")
    private String spec;
    @ApiModelProperty(value = "多属性商品属性值组")
    private List<String> attributeValues = new ArrayList<>();
}