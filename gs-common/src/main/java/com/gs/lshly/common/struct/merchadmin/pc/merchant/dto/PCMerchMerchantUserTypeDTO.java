package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-25
*/
public abstract class PCMerchMerchantUserTypeDTO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("会员类型名")
        private String userTypeName;

        @ApiModelProperty("折扣率")
        private BigDecimal ratio;

        @ApiModelProperty("折扣对应的sku商品信息列表")
        private List<PCMerchMerchantUserTypeRatioDTO.ETO> UserTypeRatioEto = new ArrayList<>();

    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeDTO.IdDTO")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        private String id;

    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        private List<String> idList;

    }

}
