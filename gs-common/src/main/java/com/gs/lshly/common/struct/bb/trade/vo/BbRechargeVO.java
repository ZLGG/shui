package com.gs.lshly.common.struct.bb.trade.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
/**
* @author oy
* @since 2020-10-28
*/
public abstract class BbRechargeVO implements Serializable {

    @Data
    @ApiModel("BbRechargeVO.PhoneBillVO")
    @Accessors(chain = true)
    public static class PhoneBillVO implements Serializable{

        @ApiModelProperty("手机号码")
        private String phone;


        @ApiModelProperty("真实姓名")
        private String realName;


        @ApiModelProperty("话费余额")
        private BigDecimal phoneBill;


        @ApiModelProperty("所属地区")
        private String region;

        
    }

}
