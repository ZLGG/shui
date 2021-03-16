package com.gs.lshly.common.struct.bbb.h5.foundation.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbbH5DataArticleVO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("标题")
        private String title;


        @ApiModelProperty("内容")
        private Blob content;


        @ApiModelProperty("文章图标")
        private String logo;


        @ApiModelProperty("阅读量")
        private Integer readCount;


        @ApiModelProperty("是否PC显示[10=PC 20=H5  30=全部]")
        private Integer pcShow;


        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;


        @ApiModelProperty("排序")
        private Integer idx;


        @ApiModelProperty("发布时间")
        private LocalDateTime sendTime;

    }

}
