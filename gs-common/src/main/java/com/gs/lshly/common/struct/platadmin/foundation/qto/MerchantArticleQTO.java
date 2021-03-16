package com.gs.lshly.common.struct.platadmin.foundation.qto;
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
* @since 2020-10-29
*/
public abstract class MerchantArticleQTO implements Serializable {

    @Data
    @ApiModel("MerchantArticleQTO.QTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class QTO extends BaseQTO {

        @ApiModelProperty("标题")
        private String title;


        @ApiModelProperty("商家选择")
        private String merchantId;
    }
}
