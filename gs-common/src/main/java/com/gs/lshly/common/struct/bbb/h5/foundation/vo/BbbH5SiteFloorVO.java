package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class BbbH5SiteFloorVO implements Serializable {


    @Data
    @ApiModel("BbbH5SiteFloorVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("楼层最大商品显示数量")
        private Integer goodsMax;

        @ApiModelProperty("广告图")
        private String topImage;

        @ApiModelProperty("广告图跳转地址")
        private String jumpUrl;

    }

    @Data
    @ApiModel("BbbH5SiteFloorVO.GoodsListVO")
    public static class GoodsListVO implements Serializable {

        @ApiModelProperty(value = "楼层ID")
        private String floorId;

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "楼层的商品")
        private BbbH5GoodsInfoVO.HomeInnerServiceVO goodsInfo;

    }
}
