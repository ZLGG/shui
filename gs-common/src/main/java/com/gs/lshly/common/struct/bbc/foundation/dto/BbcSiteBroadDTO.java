package com.gs.lshly.common.struct.bbc.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-03
*/
public abstract class BbcSiteBroadDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteBroadDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("文本内容")
        private String text;

        @ApiModelProperty("跳转地址")
        private String linkUrl;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("BbcSiteBroadDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
