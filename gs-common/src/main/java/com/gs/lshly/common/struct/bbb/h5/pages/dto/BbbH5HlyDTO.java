package com.gs.lshly.common.struct.bbb.h5.pages.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5HlyDTO implements Serializable {

    @Data
    @ApiModel("BbbH5HlyDTO.DTO")
    @Accessors(chain = true)
    public static class DTO extends BaseDTO {
        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]" )
        private Integer subject;
    }
}
