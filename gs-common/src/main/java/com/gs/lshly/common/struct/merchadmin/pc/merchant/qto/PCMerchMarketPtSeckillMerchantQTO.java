package com.gs.lshly.common.struct.merchadmin.pc.merchant.qto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月8日 下午2:16:45
 */
@SuppressWarnings("serial")
public abstract class PCMerchMarketPtSeckillMerchantQTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("PCMerchMarketPtSeckillMerchantQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("0=我的报名 1=历史报名")
        private Integer isHistory;
    }
}
