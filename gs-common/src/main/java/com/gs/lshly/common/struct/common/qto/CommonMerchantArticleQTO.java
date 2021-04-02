package com.gs.lshly.common.struct.common.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 17:46 2021/3/21
 */
public abstract class CommonMerchantArticleQTO implements Serializable {

    @Data
    @ApiModel("CommonMerchantArticleQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty(value = "显示类型[10=pc 20=h5 30=全部]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "店铺id",hidden = true)
        private String shopId;
    }
}
