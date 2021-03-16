package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class DataArticleRelationCategoryDTO implements Serializable {

    @Data
    @ApiModel("DataArticleRelationCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private Long id;

        @ApiModelProperty("文章栏目ID")
        private String categoryId;

        @ApiModelProperty("文章ID")
        private String articleId;
    }

    @Data
    @ApiModel("DataArticleRelationCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private Long id;
    }


}
