package com.gs.lshly.common.struct.common.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Starry
 * @Date 17:42 2021/3/21
 */
public  abstract class CommonMerchantArticleVO implements Serializable {


    @Data
    @ApiModel("CommonMerchantArticleVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("显示类型[10=pc 20=h5 30=全部]")
        private Integer pcShow;

        @ApiModelProperty("文章栏目ID")
        private String categoryId;

        @ApiModelProperty("文章栏目名称")
        private String categoryName;

        @ApiModelProperty("审核状态")
        private Integer state;

        @ApiModelProperty("拒绝审核原因")
        private String rejectWhy;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("发布时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime sendTime;

        @ApiModelProperty("更新时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("CommonMerchantArticleVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
