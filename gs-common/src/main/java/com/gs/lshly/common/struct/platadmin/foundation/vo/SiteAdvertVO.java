package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author hyy
* @since 2020-11-03
*/
public abstract class SiteAdvertVO implements Serializable {

    @Data
    @ApiModel("SiteAdvertVO.H5CategoryListVO")
    @Accessors(chain = true)
    public static class H5CategoryListVO implements Serializable{

        @ApiModelProperty(value = "商品类目ID")
        private String categoryId;

        @ApiModelProperty(value = "类目广告图数组")
        private List<H5AdvertItemVO> advertList = new ArrayList<>();
    }

    @Data
    @ApiModel("SiteAdvertVO.H5AdvertItemVO")
    @Accessors(chain = true)
    public static class H5AdvertItemVO implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }

    @Data
    @ApiModel("SiteAdvertVO.H5SubjectListVO")
    @Accessors(chain = true)
    public static class H5SubjectListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }

    @Data
    @ApiModel("SiteAdvertVO.PCGroupListVO")
    @Accessors(chain = true)
    public static class PCGroupListVO implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("排序号")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteAdvertVO.PCBillBoardListVO")
    @Accessors(chain = true)
    public static class PCBillBoardListVO implements Serializable{

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }

}
