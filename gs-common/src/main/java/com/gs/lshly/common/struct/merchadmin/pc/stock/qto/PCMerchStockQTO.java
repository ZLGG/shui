package com.gs.lshly.common.struct.merchadmin.pc.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class PCMerchStockQTO implements Serializable {

    @Data
    @ApiModel("PCMerchStockQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

      /*  @ApiModelProperty("商家ID")
        private String merchantId;

        @ApiModelProperty("店铺ID")
        private String shopId;

        @ApiModelProperty("商品ID")
        private String goodsId;//商品ID

        @ApiModelProperty("数据来源[10=POS 20=商家 30=order 40=采购单]")
        private Integer dataFromType;

        @ApiModelProperty("动作类型[10=同步 20=修改]")
        private Integer dataActionType;

        @ApiModelProperty("SKU")
        private String skuId;

        @ApiModelProperty("数量")
        private Integer changeQuantity;

        @ApiModelProperty("总量")
        private Integer allQuantity;

        @ApiModelProperty("备注")
        private String remark;*/
    }
}
