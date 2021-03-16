package com.gs.lshly.common.struct.bbc.trade.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsImgQTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("售后表ID")
        private String rightsId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("凭证图片")
        private String rightsImg;
    }
}
