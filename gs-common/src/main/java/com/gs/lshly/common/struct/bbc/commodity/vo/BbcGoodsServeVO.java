package com.gs.lshly.common.struct.bbc.commodity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author hanly
 * @Date 14:12 2021/05/25
 */
public abstract class BbcGoodsServeVO implements Serializable {
    @Data
    @ApiModel("BbcGoodsServeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        /**
         * 服务id
         */
        @ApiModelProperty(value = "服务id")
        private String id;

        /**
         * 服务名称
         */
        @ApiModelProperty("服务名称")
        private String serveName;

        /**
         * 服务说明
         */
        @ApiModelProperty("服务说明")
        private String serveContext;

        /**
         * 图片地址
         */
        @ApiModelProperty("图片地址")
        private String imageUrl;

        /**
         * 跳转页面地址
         */
        @ApiModelProperty("跳转页面地址")
        private String jumpUrl;
    }
}
