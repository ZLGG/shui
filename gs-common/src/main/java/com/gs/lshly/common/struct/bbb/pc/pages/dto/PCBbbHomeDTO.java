package com.gs.lshly.common.struct.bbb.pc.pages.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class PCBbbHomeDTO implements Serializable {

    @Data
    @ApiModel("PCBbbHomeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;


    }


}
