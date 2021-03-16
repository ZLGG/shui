package com.gs.lshly.common.struct.merchadmin.pc.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zzg
* @since 2020-10-22
*/
public abstract class PCMerchStockDTO implements Serializable {

    @Data
    @ApiModel("PCMerchStockDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("数据来源[10=POS 20=商家 30=order 40=采购单]")
        private Integer dataFromType;

        @ApiModelProperty("动作类型[10=同步 20=修改]")
        private Integer dataActionType;//动作类型

        @ApiModelProperty("SKU")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer changeQuantity;

        @ApiModelProperty("总量")
        private Integer allQuantity;

        @ApiModelProperty("备注")
        private String remark;
    }

    @Data
    @ApiModel("PCMerchStockDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "ID")
        private String id;
    }


}
