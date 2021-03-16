package com.gs.lshly.common.struct.bbc.trade.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
/**
* @author oy
* @since 2020-12-06
*/
public abstract class BbcTradeRightsImgDTO implements Serializable {

    @Data
    @ApiModel("BbcTradeRightsImgDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "售后凭证ID",hidden = true)
        private String id;

        @ApiModelProperty("售后表ID")
        private String rightsId;

        @ApiModelProperty("订单ID")
        private String tradeId;

        @ApiModelProperty("凭证图片")
        private String rightsImg;
    }

    @Data
    @ApiModel("BbcTradeRightsImgDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "售后凭证ID")
        private String id;
    }


}
