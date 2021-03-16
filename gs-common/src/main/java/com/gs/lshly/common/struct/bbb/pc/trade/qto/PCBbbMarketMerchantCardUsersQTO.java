package com.gs.lshly.common.struct.bbb.pc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2021-01-08
*/
public abstract class PCBbbMarketMerchantCardUsersQTO implements Serializable {

    @Data
    @ApiModel("PCBbbMarketMerchantCardUsersQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("状态[10=未使用 20=已使用 30=已过期]")
        private Integer state;

        @ApiModelProperty("过期时间[10=升序 20=降序]")
        private Integer expirationTime;

        @ApiModelProperty("优惠金额[10=升序 20=降序]")
        private Integer preferentialAmount;

    }
}
