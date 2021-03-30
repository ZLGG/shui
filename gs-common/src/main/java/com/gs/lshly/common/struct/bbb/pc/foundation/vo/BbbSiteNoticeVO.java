package com.gs.lshly.common.struct.bbb.pc.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午4:30:19
 */
public abstract class BbbSiteNoticeVO implements Serializable {

    @Data
    @ApiModel("BbbSiteNoticeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("名称")
        private String name;

    }
    
    @Data
    @ApiModel("BbbSiteNoticeVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("名称")
        private String name;
        
        @ApiModelProperty("内容")
        private String content;

    }
}
