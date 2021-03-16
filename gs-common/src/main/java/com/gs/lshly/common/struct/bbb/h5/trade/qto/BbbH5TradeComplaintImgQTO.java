package com.gs.lshly.common.struct.bbb.h5.trade.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-12-23
*/
public abstract class BbbH5TradeComplaintImgQTO implements Serializable {

    @Data
    @ApiModel("BbbH5TradeComplaintImgQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("订单投诉id")
        private String complaintId;

        @ApiModelProperty("图片地址")
        private String imgUrl;
    }
}
