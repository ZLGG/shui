package com.gs.lshly.common.struct.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Starry
 * @Date 16:17 2021/3/30
 */
public abstract class CommonSiteActiveVO {

    @Data
    @Accessors(chain = true)
    @ApiModel("CommonSiteActiveVO.ListVO")
    public static class ListVO implements Serializable{

        @ApiModelProperty("活动配置主键id")
        private String id;


        @ApiModelProperty("楼层名称")
        private String floorName;


        @ApiModelProperty("图片地址")
        private String imgUrl;


        @ApiModelProperty("图片跳转地址")
        private String imgSkipUrl;


        @ApiModelProperty("10=2b 20=2c")
        private Integer terminal;


        @ApiModelProperty("10=pc 20=h5")
        private Integer pcShow;




    }
}
