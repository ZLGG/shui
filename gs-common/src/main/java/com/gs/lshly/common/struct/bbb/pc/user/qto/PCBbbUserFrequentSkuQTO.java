package com.gs.lshly.common.struct.bbb.pc.user.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class PCBbbUserFrequentSkuQTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentSkuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("常购商品ID")
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
}
