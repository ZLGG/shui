package com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.qto;
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
* @since 2020-12-08
*/
public abstract class PCMerchGoodsFupinQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsFupinQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String goodsId;
    }
}
