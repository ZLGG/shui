package com.gs.lshly.common.struct.bbb.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.common.CommonShopDTO;
import com.gs.lshly.common.struct.common.LegalDictDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-10-22
*/
public abstract class PCBbbMerchantApplyDTO implements Serializable {

    @Data
    @ApiModel("PCBbbMerchantApplyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺信息")
        private CommonShopDTO.ETO shopETO;

        @ApiModelProperty("法人信息")
        private LegalDictDTO.ETO legalDictETO;

    }

    @Data
    @ApiModel("PCBbbMerchantApplyDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
