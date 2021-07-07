package com.gs.lshly.common.struct.platadmin.foundation.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * @author chenyang
 */
@SuppressWarnings("serial")
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
    
    @Data
    @ApiModel("BasicAreasVO.AddressListVO")
    @Accessors(chain = true)
    public static class AddressListVO implements Serializable {
        @ApiModelProperty("城市id")
        private Integer value;

        @ApiModelProperty("城市名")
        private String text;
    }
}
