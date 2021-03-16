package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class MerchantAgreementQTO implements Serializable {

    @Data
    @ApiModel("MerchantAgreementQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("协议内容")
        private String content;
    }
}
