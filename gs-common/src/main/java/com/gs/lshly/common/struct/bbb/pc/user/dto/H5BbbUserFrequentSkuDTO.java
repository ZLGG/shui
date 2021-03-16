package com.gs.lshly.common.struct.bbb.pc.user.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class H5BbbUserFrequentSkuDTO implements Serializable {

    @Data
    @ApiModel("H5BbbUserFrequentSkuDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("常购ID")
        private String frequentId;

        @ApiModelProperty("spu-id")
        private String goodsId;

        @ApiModelProperty("sku-id")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer quantity;

        @ApiModelProperty("会员ID")
        private String userId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商家ID")
        private String merchantId;
    }

    @Data
    @ApiModel("H5BbbUserFrequentSkuDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


}
