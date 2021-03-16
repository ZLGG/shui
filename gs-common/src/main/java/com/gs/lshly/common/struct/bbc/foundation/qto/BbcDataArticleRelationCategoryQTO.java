package com.gs.lshly.common.struct.bbc.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class BbcDataArticleRelationCategoryQTO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleRelationCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("文章栏目ID")
        private String categoryId;

        @ApiModelProperty("文章ID")
        private String articleId;
    }
}
