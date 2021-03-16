package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
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
* @since 2020-12-01
*/
public abstract class PCMerchMarketPtActivityMerchantQTO implements Serializable {

    @Data
    @ApiModel("PCMerchMarketPtActivityMerchantQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("0=我的报名 1=历史报名")
        private Integer isHistory;
    }
}
