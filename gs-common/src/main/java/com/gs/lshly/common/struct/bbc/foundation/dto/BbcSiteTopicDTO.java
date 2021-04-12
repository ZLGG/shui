package com.gs.lshly.common.struct.bbc.foundation.dto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbcSiteTopicDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteTopicDTO.ListByTopicNameDTO")
    @Accessors(chain = true)
    public static class ListByTopicNameDTO extends BaseDTO {
    	
    	@ApiModelProperty("返回数量")
    	private Integer size;

        @ApiModelProperty("分类名称")
        private String name;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

}
