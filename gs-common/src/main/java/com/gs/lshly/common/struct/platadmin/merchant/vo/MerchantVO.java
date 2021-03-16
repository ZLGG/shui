package com.gs.lshly.common.struct.platadmin.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-08
*/
public abstract class MerchantVO implements Serializable {

    @Data
    @ApiModel("MerchantVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("商家名称")
        private String merchantName;


        @ApiModelProperty("商家头像")
        private String merchantHeadImg;


        @ApiModelProperty("法人单位ID")
        private String legalId;


        @ApiModelProperty("主帐号ID")
        private String mainAccountId;




    }

    @Data
    @ApiModel("MerchantVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
