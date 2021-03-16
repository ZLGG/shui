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
public abstract class BbcSiteNavigationVO implements Serializable {

    @Data
    @ApiModel("BbcSiteNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("导航名称")
        private String name;

        @ApiModelProperty("链接地址")
        private String url;

        @ApiModelProperty("热点图片")
        private String hotImageUrl;
    }

}
