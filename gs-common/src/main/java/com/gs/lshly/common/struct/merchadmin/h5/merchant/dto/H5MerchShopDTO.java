package com.gs.lshly.common.struct.merchadmin.h5.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
/**
* @author zst
* @since 2021-01-20
*/
public abstract class H5MerchShopDTO implements Serializable {

    @Data
    @ApiModel("H5MerchShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {


        @ApiModelProperty(value = "id")
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
    }

    @Data
    @ApiModel("H5MerchShopDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }



}
