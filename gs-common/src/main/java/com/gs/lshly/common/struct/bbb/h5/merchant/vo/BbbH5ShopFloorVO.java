package com.gs.lshly.common.struct.bbb.h5.merchant.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-11-05
*/
public abstract class BbbH5ShopFloorVO implements Serializable {

    @Data
    @ApiModel("BbcShopFloorVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("楼层ID")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("最大商品数量")
        private Integer showNum;

    }
}
