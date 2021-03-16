package com.gs.lshly.common.struct.platadmin.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author Starry
* @since 2020-09-29
*/
public abstract class GoodsParamInfoDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "参数id",hidden = true)
        private String id;

        @ApiModelProperty("参数组id")
        private String paramsId;

        @ApiModelProperty("参数值")
        private String name;

        @ApiModelProperty("参数别名")
        private String alias;

    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "参数id")
        private String id;
    }
}
