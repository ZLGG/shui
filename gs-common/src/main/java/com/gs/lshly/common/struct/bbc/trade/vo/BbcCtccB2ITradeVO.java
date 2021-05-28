package com.gs.lshly.common.struct.bbc.trade.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * B2I返回消息
 *
 * 
 * @author yingjun
 * @date 2021年5月28日 下午3:22:57
 */
@SuppressWarnings("serial")
public abstract class BbcCtccB2ITradeVO implements Serializable {
	
	
	/**
	 * 返回的消息
	 *
	 * 
	 * @author yingjun
	 * @date 2021年5月28日 下午3:23:38
	 */
	@Data
	@ApiModel("BbcCtccB2ITradeVO.ResultVO")
	@Accessors(chain = true)
	public static class ResultVO implements Serializable{
		
		@ApiModelProperty("返回头信息")
        private Boolean success;
    
    	@ApiModelProperty("返回信息：中台订单号")
        private String data;
    	
    	@ApiModelProperty("接口调用结果编码")
        private String code;
        
        @ApiModelProperty("结果描述")
        private String message;
        
	}
}
