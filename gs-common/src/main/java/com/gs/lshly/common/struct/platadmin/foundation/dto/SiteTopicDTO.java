package com.gs.lshly.common.struct.platadmin.foundation.dto;
import java.io.Serializable;
import java.util.List;

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
public abstract class SiteTopicDTO implements Serializable {


    @Data
    @ApiModel("SiteTopicDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

    	@ApiModelProperty(value = "id")
        private String id;
    	
        @ApiModelProperty(value="名称",required=true)
        private String name;
        
        @ApiModelProperty(value="描述",required=false)
        private String remark;
        
        @ApiModelProperty(value="商品列表",required=false)
        private List<String> goodsIds;
        
        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
        
        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]",hidden = true)
        private Integer subject;
        
        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;
        
        @ApiModelProperty(value="封面图",required=false)
        private String imageUrl;
        
        @ApiModelProperty(value="所属大类[所属分类，可多选 10：电信甄选 20：名品集市  ]",required=false)
        private String category;
        
        @ApiModelProperty(value="排序字段",required=false)
        private Integer idx;
        
        @ApiModelProperty(value="是否首页显示 1：是；0：否",required=false)
        private Integer isDefault;
        
        
    }


    @Data
    @ApiModel("SiteTopicDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "id")
        private String id;
    }
    
    @Data
    @ApiModel("SiteTopicDTO.OnoffDTO")
    @Accessors(chain = true)
    public static class OnoffDTO extends BaseDTO {
        @ApiModelProperty(value = "id",required=true)
        private String id;
        
        @ApiModelProperty(value="上/下架 0上架 1下架",required=true)
        private Integer onoff;
    }
}
