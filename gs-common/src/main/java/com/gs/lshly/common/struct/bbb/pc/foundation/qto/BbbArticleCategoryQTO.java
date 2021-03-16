package com.gs.lshly.common.struct.bbb.pc.foundation.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbbArticleCategoryQTO implements Serializable {

    @Data
    @ApiModel("BbbArticleCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty("标题")
        private String title;
    }

    @Data
    @ApiModel("BbbArticleCategoryQTO.ArticleQTO")
    @Accessors(chain = true)
    public static class ArticleQTO extends BaseQTO {

        @ApiModelProperty("二级栏目ID")
        private String categoryId;
    }
}
