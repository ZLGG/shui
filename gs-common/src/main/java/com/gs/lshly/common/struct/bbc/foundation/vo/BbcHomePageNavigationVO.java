package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BbcHomePageNavigationVO implements Serializable {

    @Data
    @ApiModel("BBC.SiteNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("热点图片(顶部导航专属)")
        private String hotImageUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("BBC.SiteNavigationVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
