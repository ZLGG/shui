package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月18日 下午3:20:11
 */
public abstract class BbcTravelskyDTO implements Serializable {

	/**
	 * sid=20001&productid=6752&timestamp=20091113101533"
				+ "&orderid=2009121212345676&count=1"
				+ "&mobile=13500000000&price=9000&key=123qwe12
	 *
	 * 
	 * @author yingjun
	 * @date 2021年3月18日 下午3:20:42
	 */
    @Data
    @ApiModel("BbcTravelskyDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value="交易商品id",required=true)
        private String tradeGoodsId;
        
    }



}
