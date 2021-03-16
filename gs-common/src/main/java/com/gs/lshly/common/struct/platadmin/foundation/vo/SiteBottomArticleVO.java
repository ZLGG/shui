package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-08
*/
public abstract class SiteBottomArticleVO implements Serializable {

    @Data
    @ApiModel("SiteBottomArticleVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("底部文章名")
        private String name;

        @ApiModelProperty("文章链接")
        private String articleUrl;

    }

    @Data
    @ApiModel("SiteBottomArticleVO.DetailVO")
    public static class DetailVO extends PCListVO {

    }
}
