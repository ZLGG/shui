package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopCustomAreaDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopCustomAreaDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("自定义区域内容")
        private String content;
    }

    @Data
    @ApiModel("PCMerchShopCustomAreaDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
