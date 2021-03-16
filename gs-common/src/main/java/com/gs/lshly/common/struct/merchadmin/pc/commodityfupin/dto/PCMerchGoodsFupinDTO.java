package com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-12-08
*/
public abstract class PCMerchGoodsFupinDTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsFupinDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "扶贫商品id",hidden = true)
        private String id;

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("操作人")
        private String operator;

        @ApiModelProperty("图片凭证列表")
        private List<PCMerchGoodsFupinImageDTO.ETO> etoList = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchGoodsFupinDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "商品id")
        private String goodsId;
    }


}
