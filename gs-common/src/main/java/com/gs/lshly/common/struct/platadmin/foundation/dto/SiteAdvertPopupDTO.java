package com.gs.lshly.common.struct.platadmin.foundation.dto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.platadmin.commodity.dto.GoodsInfoDTO;

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
public abstract class SiteAdvertPopupDTO implements Serializable {


    @Data
    @ApiModel("SiteAdvertPopupDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    	@ApiModelProperty(value = "id")
        private String id;
    	
        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value="广告弹窗名称",required=true)
        private String name;
        
        @ApiModelProperty(value="上/下架 0上架 1下架",required=true)
        private Integer onoff;
        
        @ApiModelProperty(value="图片地址",required=true)
        private String imageUrl;

        @ApiModelProperty(value="跳转地址",required=false)
        private String jumpUrl;
        
        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;
    }


    @Data
    @ApiModel("SiteAdvertPopupDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "广告弹窗id")
        private String id;
    }
    
    @Data
    @ApiModel("SiteAdvertPopupDTO.OnoffDTO")
    @Accessors(chain = true)
    public static class OnoffDTO extends BaseDTO {
        @ApiModelProperty(value = "广告弹窗id",required=true)
        private String id;
        
        @ApiModelProperty(value="上/下架 0上架 1下架",required=true)
        private Integer onoff;
    }
}
