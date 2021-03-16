package com.gs.lshly.common.struct.platadmin.merchant.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantQTO implements Serializable {

    @Data
    @ApiModel("MerchantQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商家名称")
        private String merchantName;

        @ApiModelProperty("商家头像")
        private String merchantHeadImg;

        @ApiModelProperty("法人单位ID")
        private String legalId;

        @ApiModelProperty("主帐号ID")
        private String mainAccountId;
    }
}
