package com.gs.lshly.common.struct.platadmin.foundation.dto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午10:19:57
 */
public abstract class SiteNoticeDTO implements Serializable {


    @Data
    @ApiModel("SiteNoticeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    	@ApiModelProperty(value = "id")
        private String id;
    	
        @ApiModelProperty(value="名称",required=true)
        private String name;
        
        @ApiModelProperty(value="内容",required=true)
        private String content;
        
        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
        
        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;
    }


    @Data
    @ApiModel("SiteNoticeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "公告id")
        private String id;
    }
    
}
