package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.poi.ss.formula.functions.T;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class PCMerchShopDTO implements Serializable {

    @Data
    @ApiModel("MerchAdmin.CommonShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺名称")
        private String shopName;

        @ApiModelProperty("店铺Logo")
        private String shopLogo;

        @ApiModelProperty("店铺描述")
        private String shopDescribe;

        @ApiModelProperty("是否开启地图导航(10-关闭;20-开启)")
        private Integer isFixedMap;

        @ApiModelProperty("店铺联系人手机号")
        private String shopManPhone;


        @ApiModelProperty("拉卡拉商户号")
        private String lakalaNo;



    }

    @Data
    @ApiModel("MerchAdmin.CommonShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("MerchAdmin.CommonShopDTO.MerchantIdDTO")
    @AllArgsConstructor
    public static class MerchantIdDTO extends BaseDTO {
        @ApiModelProperty(value = "merchantId")
        private String merchantId;
    }

}
