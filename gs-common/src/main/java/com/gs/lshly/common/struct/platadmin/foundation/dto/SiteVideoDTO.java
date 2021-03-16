package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-20
*/
public abstract class SiteVideoDTO implements Serializable {


    @Data
    @ApiModel("SiteVideoDTO.H5DTO")
    @Accessors(chain = true)
    public static class H5DTO extends BaseDTO {

        @ApiModelProperty("id")
        private List<H5ItemDTO> videoList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;
    }

    @Data
    @ApiModel("SiteVideoDTO.PCDTO")
    @Accessors(chain = true)
    public static class PCDTO extends BaseDTO {

        @ApiModelProperty("id")
        private List<PCItemDTO> videoList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;
    }


    @Data
    @ApiModel("SiteVideoDTO.H5ItemDTO")
    @Accessors(chain = true)
    public static class  H5ItemDTO extends BaseDTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("封面图宽")
        private Integer imageW;

        @ApiModelProperty("封面图高")
        private Integer imageH;

        @ApiModelProperty("视频链接")
        private String videoUrl;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

    }

    @Data
    @ApiModel("SiteVideoDTO.PCItemDTO")
    @Accessors(chain = true)
    public static class  PCItemDTO extends BaseDTO {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("视频封面图片")
        private String imageUrl;

        @ApiModelProperty("封面图宽")
        private Integer imageW;

        @ApiModelProperty("封面图高")
        private Integer imageH;

        @ApiModelProperty("视频链接")
        private String videoUrl;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

    }

}
