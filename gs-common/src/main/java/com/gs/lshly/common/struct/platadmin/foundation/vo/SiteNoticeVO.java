package com.gs.lshly.common.struct.platadmin.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
 * 公告配置
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:25:05
 */
public abstract class SiteNoticeVO implements Serializable {


    @Data
    @ApiModel("SiteNoticeVO.PCListVO")
    @Accessors(chain = true)
    public static class PCListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("名称")
        private String name;

    }
    
    
    @Data
    @ApiModel("SiteNoticeVO.PCDetailVO")
    @Accessors(chain = true)
    public static class PCDetailVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("名称")
        private String name;

        @ApiModelProperty("内容")
        private String content;
        
    }
}
