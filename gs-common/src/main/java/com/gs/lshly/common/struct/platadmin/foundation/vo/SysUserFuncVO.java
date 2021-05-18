package com.gs.lshly.common.struct.platadmin.foundation.vo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 专题配置
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:25:05
 */
public abstract class SysUserFuncVO implements Serializable {

    @Data
    @ApiModel("SysUserVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

    	@ApiModelProperty(value = "菜单功能id")
        private String id;

        @ApiModelProperty(value = "名称")
        private String name;

        @ApiModelProperty(value = "图标")
        private String icon;

        @ApiModelProperty(value = "顺序")
        private String idx;

        @ApiModelProperty("路由")
        private String frontRouter;
        
        @ApiModelProperty("子目录")
        private List<ListVO> children;
    }
    
}
