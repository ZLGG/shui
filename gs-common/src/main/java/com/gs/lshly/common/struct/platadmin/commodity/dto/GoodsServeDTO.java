package com.gs.lshly.common.struct.platadmin.commodity.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @Author hanly
 * @Date 14:12 2021/05/25
 */
public abstract class GoodsServeDTO implements Serializable {
    @Data
    @ApiModel("GoodsServeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {
        /**
         * 服务id
         */
        @ApiModelProperty(value = "服务id", hidden = true)
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

    @Data
    @ApiModel("GoodsServeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "服务id")
        private String id;
    }

    @Data
    @ApiModel("GoodsServeDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "服务id")
        private List<String> idList;
    }
}
