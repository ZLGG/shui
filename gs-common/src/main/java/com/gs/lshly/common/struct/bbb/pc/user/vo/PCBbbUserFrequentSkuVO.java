package com.gs.lshly.common.struct.bbb.pc.user.vo;
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
public abstract class PCBbbUserFrequentSkuVO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentSkuVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("ID")
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
    @ApiModel("PCBbbUserFrequentSkuVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
