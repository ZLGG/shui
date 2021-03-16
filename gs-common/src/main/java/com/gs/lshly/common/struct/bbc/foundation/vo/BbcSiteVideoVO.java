package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbcSiteVideoVO implements Serializable {

    @Data
    @ApiModel("BbcSiteVideoVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("视频封面宽")
        private Integer imageW;

        @ApiModelProperty("视频封面高")
        private Integer imageH;

        @ApiModelProperty("视频地址")
        private String videoUrl;


    }

}
