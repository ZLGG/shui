package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author chenyang
 */
public class BasicAreasVO implements Serializable {

    @Data
    @ApiModel("BasicAreasVO.DropListVO")
    @Accessors(chain = true)
    public static class DropListVO implements Serializable {
        @ApiModelProperty("城市id")
        private Integer id;

        @ApiModelProperty("城市名")
        private String name;
    }
}
