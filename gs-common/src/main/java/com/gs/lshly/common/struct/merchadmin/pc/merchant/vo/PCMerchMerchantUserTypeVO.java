package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gs.lshly.common.utils.BigDecimalSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-25
*/
public abstract class PCMerchMerchantUserTypeVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantUserTypeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("Id")
        private String id;

        @ApiModelProperty("会员类型名")
        private String userTypeName;

        @ApiModelProperty("折扣率")
        @JsonSerialize(using = BigDecimalSerialize.class)
        private BigDecimal ratio;
    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("会员类型绑定的商品信息列表")
        private List<GoodsVo> userTypeRatioEto = new ArrayList<>();
    }

    @Data
    @ApiModel("PCMerchMerchantUserTypeVO.GoodsVo")
    public static class GoodsVo implements Serializable {
        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("skuId")
        private String skuId;
    }
}
