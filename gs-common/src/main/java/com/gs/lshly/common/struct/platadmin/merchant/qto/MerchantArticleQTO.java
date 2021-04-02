package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author xxfc
* @since 2020-11-09
*/
public abstract class MerchantArticleQTO implements Serializable {

    @Data
    @ApiModel("MerchantArticleQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("店铺")
        private String merchantId;

        @ApiModelProperty("文章审核状态：10-待审，20-通过，30-拒绝")
        private Integer state;

    }

}
