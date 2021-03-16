package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author 大飞船
 * @since 2020-09-29
 */
public abstract class SiteFloorGoodsVO implements Serializable {

    @Data
    @ApiModel("SiteFloorGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("排序")
        private Integer idx;
    }

}
