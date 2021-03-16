package com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto;
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
* @since 2020-12-08
*/
public abstract class PCMerchGoodsFupinImageDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsFupinImageDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "图片凭证id",hidden = true)
        private String id;

        @ApiModelProperty("扶贫商品id")
        private String fupinGoodsId;

        @ApiModelProperty("图片凭证地址")
        private String imgUrl;

        @ApiModelProperty("操作人")
        private String operator;
    }

    @Data
    @ApiModel("PCMerchGoodsFupinImageDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "图片凭证id")
        private String id;
    }


}
