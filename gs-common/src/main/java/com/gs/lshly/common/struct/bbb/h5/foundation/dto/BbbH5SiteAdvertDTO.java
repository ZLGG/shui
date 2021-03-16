package com.gs.lshly.common.struct.bbb.h5.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbbH5SiteAdvertDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteAdvertDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
