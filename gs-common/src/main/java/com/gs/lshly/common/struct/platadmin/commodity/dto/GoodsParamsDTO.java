package com.gs.lshly.common.struct.platadmin.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-09-29
*/
public abstract class GoodsParamsDTO implements Serializable {

    @Data
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "参数组id",hidden = true)
        private String id;

        @ApiModelProperty("参数组名")
        private String name;

        @ApiModelProperty(value = "参数列表")
        private List<GoodsParamInfoDTO.ETO> paramInfos;
    }

    @Data
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "参数组id")
        private String id;
    }
}
