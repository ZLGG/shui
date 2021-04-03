package com.gs.lshly.common.struct.bbb.pc.foundation.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月11日 下午4:30:19
 */
public abstract class BbbSiteTopicVO implements Serializable {

    @Data
    @ApiModel("BbbSiteTopicVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
        private String id;

        @ApiModelProperty("图片连接地址")
        private String imageUrl;

    }
    
}
