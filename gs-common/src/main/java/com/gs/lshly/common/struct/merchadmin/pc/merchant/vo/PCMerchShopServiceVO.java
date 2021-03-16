package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-09
*/
public abstract class PCMerchShopServiceVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopServiceVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("状态/是否启用[10=启用 20=禁用]")
        private Integer state;

        @ApiModelProperty("账号")
        private String account;
    }


    @Data
    @ApiModel("PCMerchShopServiceVO.PhoneVO")
    @Accessors(chain = true)
    public static class PhoneVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("热线电话")
        private String phone;

        @ApiModelProperty("热线电话状态/是否启用[10=启用 20=禁用]")
        private Integer phoneState;

    }

    @Data
    @ApiModel("PCMerchShopServiceVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
