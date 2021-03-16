package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-19
*/
public abstract class DataArticleVO implements Serializable {

    @Data
    @ApiModel("DataArticleVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("栏目数组")
        private List<String> categoryNameList = new ArrayList<>();

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("logo")
        private String logo;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("阅读量")
        private Integer readCount;

        @ApiModelProperty("是否PC显示[10=PC 20=H5  30=全部]")
        private Integer pcShow;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("发布时间")
        private LocalDateTime sendTime;

    }


    @Data
    @ApiModel("DataArticleVO.DVO")
    @Accessors(chain = true)
    public static class DVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("logo")
        private String logo;

        @ApiModelProperty("栏目id")
        private List<String> categoryIds = new ArrayList<>();

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault;

        @ApiModelProperty("是否PC显示[10=PC 20=H5  30=全部]")
        private Integer pcShow;

    }

}
