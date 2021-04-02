package com.gs.lshly.common.struct.common.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author Starry
 * @Date 16:17 2021/3/30
 */
public abstract class CommonSiteActiveDTO {

    @Data
    @ApiModel("CommonSiteActiveDTO.QueryDTO")
    public static class QueryDTO extends BaseDTO {

        @ApiModelProperty("10=2b 20=2c")
        private Integer terminal;

        @ApiModelProperty("10=pc 20=h5")
        private Integer pcShow;
    }
}
