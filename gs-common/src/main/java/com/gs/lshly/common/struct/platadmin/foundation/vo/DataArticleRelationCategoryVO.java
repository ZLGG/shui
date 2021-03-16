package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author 陈奇
* @since 2020-10-19
*/
public abstract class DataArticleRelationCategoryVO implements Serializable {

    @Data
    @ApiModel("DataArticleRelationCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("")
        private Long id;


        @ApiModelProperty("文章栏目ID")
        private String categoryId;


        @ApiModelProperty("文章ID")
        private String articleId;

    }

    @Data
    @ApiModel("DataArticleRelationCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
