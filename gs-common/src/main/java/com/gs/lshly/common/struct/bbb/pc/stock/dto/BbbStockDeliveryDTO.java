package com.gs.lshly.common.struct.bbb.pc.stock.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
* @author lxus
* @since 2020-11-02
*/
public abstract class BbbStockDeliveryDTO implements Serializable {

    @Data
    @ApiModel("店铺支持的物流方式-参数对象")
    @Accessors(chain = true)
    public static class SupportDeliveryTypeDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺id")
        private String shopId;

    }

    @Data
    @ApiModel("店铺商品运费计算-参数对象")
    @Accessors(chain = true)
    public static class DeliveryAmountDTO extends BaseDTO {

        @ApiModelProperty("配送类型(必填)[10=快递 20=自提 30=门店配送]")
        private Integer deliveryType;

        @ApiModelProperty(value = "sku集合(必填)")
        private List<DeliverySKUDTO> skus;

        @ApiModelProperty(value = "店铺id(配送类型为30=门店配送时，必填)")
        private String shopId;

        @ApiModelProperty(value = "收货地址id(配送类型为(10=快递,30=门店配送)时，必填)")
        private String addressId;

    }

    @Data
    @ApiModel("店铺商品运费计算-sku参数对象")
    @Accessors(chain = true)
    public static class DeliverySKUDTO extends BaseDTO {

        @ApiModelProperty(value = "sku集合")
        private String skuId;

        @ApiModelProperty(value = "单位数量")
        private BigDecimal amount;

    }

}
