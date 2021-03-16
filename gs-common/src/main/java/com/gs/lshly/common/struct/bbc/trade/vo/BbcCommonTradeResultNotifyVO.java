package com.gs.lshly.common.struct.bbc.trade.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author oy
* @since 2020-10-28
*/
public abstract class BbcCommonTradeResultNotifyVO implements Serializable {

    @Data
    @ApiModel("BbcCommonTradeResultNotifyVO.notifyVO")
    @Accessors(chain = true)
    public static class notifyVO implements Serializable {

        private String notifyXml;

    }

}
