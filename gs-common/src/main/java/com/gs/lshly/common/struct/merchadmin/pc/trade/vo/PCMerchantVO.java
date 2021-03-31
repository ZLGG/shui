package com.gs.lshly.common.struct.merchadmin.pc.trade.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月31日 上午11:26:23
 */
public abstract class PCMerchantVO implements Serializable {

    @Data
    @ApiModel("MerchantVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO implements Serializable{
    	@ApiModelProperty("主键")
        private String id;
    	@ApiModelProperty("商户名称")
        private String merchantName;
    	@ApiModelProperty("联系地址")
        private String address;
    	@ApiModelProperty("商户类别（10=积分商户 20=普通商户）")
        private Integer type;
    	@ApiModelProperty("商户属地（省名称）")
        private String province;
    	@ApiModelProperty("商户属地（市名称）")
        private String city;
    	@ApiModelProperty("协议到期时间")
        private String expirationTime;
    	@ApiModelProperty("协议号")
        private String agreementCode;
    	
    	@ApiModelProperty("联系人")
        private String linkMan;
    	@ApiModelProperty("联系手机号码")
        private String linkPhone;
    	@ApiModelProperty("EMAIL")
        private String email;

    }
}
