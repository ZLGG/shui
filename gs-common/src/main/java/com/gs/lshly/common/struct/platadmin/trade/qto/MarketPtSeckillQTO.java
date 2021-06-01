package com.gs.lshly.common.struct.platadmin.trade.qto;
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
 * @date 2021年5月7日 上午10:44:13
 */
@SuppressWarnings("serial")
public abstract class MarketPtSeckillQTO implements Serializable {

    @Data
    @EqualsAndHashCode(callSuper=false)
    @ApiModel("MarketPtSeckillQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

/*        @ApiModelProperty(value = "状态[10=未审核 20=审核通过 30=审核未通过 40=全部]")
        private String check;

        @ApiModelProperty(value = "店铺名字")
        private String shopName;*/

        @ApiModelProperty(value = "活动名称")
        private String acName;

    }
}
