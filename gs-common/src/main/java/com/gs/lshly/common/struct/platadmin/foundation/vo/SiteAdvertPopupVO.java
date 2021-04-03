package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 广告弹窗
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:25:05
 */
public abstract class SiteAdvertPopupVO implements Serializable {

    @Data
    @ApiModel("SiteAdvertPopupVO.H5ListVO")
    @Accessors(chain = true)
    public static class H5ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("文字")
        private String text;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("SiteAdvertPopupVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;
        
        @ApiModelProperty("上线/下线  1/0 ")
        private Integer onoff;

    }
    
    
    @Data
    @ApiModel("SiteAdvertPopupVO.PCDetailVO")
    @Accessors(chain = true)
    public static class PCDetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String imageUrl;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("跳转地址")
        private String jumpUrl;
        
        @ApiModelProperty("上线/下线  1/0 ")
        private Integer onoff;

    }
}
