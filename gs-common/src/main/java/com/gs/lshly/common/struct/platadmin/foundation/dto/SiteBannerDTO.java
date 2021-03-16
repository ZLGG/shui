package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author 大飞船
* @since 2020-09-29
*/
public abstract class SiteBannerDTO implements Serializable {

    @Data
    @ApiModel("SiteBannerDTO.H5DTO")
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class H5DTO extends BaseDTO {

        @ApiModelProperty("轮播图配置")
        private List<H5Item> bannerList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;

    }

    @Data
    @ApiModel("SiteBannerDTO.PCDTO")
    @Accessors(chain = true)
    @NoArgsConstructor
    public static class PCDTO extends BaseDTO {

        @ApiModelProperty("轮播图配置")
        private List<PCItem> bannerList;

        @ApiModelProperty("删除的数据")
        private List<String> removeIdList;

    }

    @Data
    @ApiModel("SiteBannerDTO.H5Item")
    @Accessors(chain = true)
    public static class H5Item implements Serializable{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }


    @Data
    @ApiModel("SiteBannerDTO.PCItem")
    @Accessors(chain = true)
    public static class PCItem implements Serializable{

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增")
        private Integer isNew;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

}
