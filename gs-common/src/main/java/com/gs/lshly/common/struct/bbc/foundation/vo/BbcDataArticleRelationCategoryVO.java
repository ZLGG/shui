package com.gs.lshly.common.struct.bbc.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbcDataArticleRelationCategoryVO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleRelationCategoryVO.ListVO")
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
    @ApiModel("BbcDataArticleRelationCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
