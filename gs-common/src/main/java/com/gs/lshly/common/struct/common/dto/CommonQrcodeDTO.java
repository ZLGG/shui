package com.gs.lshly.common.struct.common.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author zst
* @since 2021-01-14
*/
public abstract class CommonQrcodeDTO implements Serializable {

    @Data
    @ApiModel("CommonQrcodeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "二维码内容")
        private String content;

        @ApiModelProperty(value = "二维码宽度(不填按系统默认的)")
        private Integer width;

        @ApiModelProperty(value = "二维码高度(不填按系统默认的)")
        private Integer height;

    }

}
