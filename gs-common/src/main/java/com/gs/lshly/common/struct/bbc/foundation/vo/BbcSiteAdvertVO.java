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
* @since 2020-11-03
*/
public abstract class BbcSiteAdvertVO implements Serializable {

    @Data
    @ApiModel("BbcSiteAdvertVO.CategoryListVO")
    @Accessors(chain = true)
    public static class CategoryListVO implements Serializable{

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }

    @Data
    @ApiModel("BbcSiteAdvertVO.SubjectListVO")
    @Accessors(chain = true)
    public static class SubjectListVO implements Serializable{

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
    @ApiModel("BbcSiteAdvertVO.InnerCategoryAdvertListVO")
    @Accessors(chain = true)
    public static class InnerCategoryAdvertListVO implements Serializable{

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("文字")
        private String text;
    }
    
    
    @Data
    @ApiModel("BbcSiteAdvertVO.AdvertDetailVO")
    @Accessors(chain = true)
    public static class AdvertDetailVO implements Serializable{

        @ApiModelProperty("商品类目ID")
        private String categoryId;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("名称")
        private String name;
        
        @ApiModelProperty("是否商品类目广告")
        private Integer isCategory;
        
        @ApiModelProperty("排序")
        private Integer idx;
    }
}
