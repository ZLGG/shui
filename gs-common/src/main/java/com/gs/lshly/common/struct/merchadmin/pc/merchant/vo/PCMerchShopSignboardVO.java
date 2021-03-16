package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
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
public abstract class PCMerchShopSignboardVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopSignboardVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

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
    @ApiModel("PCMerchShopSignboardVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("图片地址")
        private String columuPicture;

        @ApiModelProperty("是否显示店铺logo[10=是 20=否]")
        private Integer isShowLogo;

        @ApiModelProperty("是否显示店铺名称[10=是 20=否]")
        private Integer isShowName;

        @ApiModelProperty("是否显示店铺描述[10=是 20=否]")
        private Integer isShowDescription;

    }
}
