package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author 陈奇
* @since 2020-10-20
*/
public abstract class SiteVideoVO implements Serializable {

    @Data
    @ApiModel("SiteVideoVO.ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("封面图宽")
        private Integer imageW;

        @ApiModelProperty("封面图高")
        private Integer imageH;

        @ApiModelProperty("视频地址")
        private String videoUrl;

    }

    @Data
    @ApiModel("SiteVideoVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("封面图宽")
        private Integer imageW;

        @ApiModelProperty("封面图高")
        private Integer imageH;

        @ApiModelProperty("视频地址")
        private String videoUrl;

    }

}
