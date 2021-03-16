package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-06
*/
public abstract class MerchantAgreementVO implements Serializable {

    @Data
    @ApiModel("MerchantAgreementVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("协议内容")
        private String content;




    }

    @Data
    @ApiModel("MerchantAgreementVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
