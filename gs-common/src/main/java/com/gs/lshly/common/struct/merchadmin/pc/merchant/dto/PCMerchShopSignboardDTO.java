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
public abstract class PCMerchShopSignboardDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopSignboardDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("图片地址")
        private String columuPicture;

        @ApiModelProperty("是否显示店铺logo[10=是 20=否]")
        private Integer isShowLogo;

        @ApiModelProperty("是否显示店铺名称[10=是 20=否]")
        private Integer isShowName;

        @ApiModelProperty("是否显示店铺描述[10=是 20=否]")
        private Integer isShowDescription;

    }

    @Data
    @ApiModel("PCMerchShopSignboardDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
