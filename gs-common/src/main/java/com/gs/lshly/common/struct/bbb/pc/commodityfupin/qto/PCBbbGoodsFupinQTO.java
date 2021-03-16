package com.gs.lshly.common.struct.bbb.pc.commodityfupin.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-12-09
*/
public abstract class PCBbbGoodsFupinQTO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsFupinQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodsId;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("操作人")
        private String operator;
    }
}
