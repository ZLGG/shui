package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-17
*/
public abstract class DataArticleCategoryVO implements Serializable {

    @Data
    @ApiModel("DataArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty("是否第一层级[10=是  20=否]")
        private Integer leveone;
    }

    @Data
    @ApiModel("DataArticleCategoryVO.FirstListVO")
    @Accessors(chain = true)
    public static class FirstListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("栏目图片")
        private String icon;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("一级下对应二级")
        List<SecondListVO> lists;
    }

    @Data
    @ApiModel("DataArticleCategoryVO.SecondListVO")
    @Accessors(chain = true)
    public static class SecondListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("栏目图片")
        private String icon;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;
    }

    @Data
    @ApiModel("DataArticleCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
