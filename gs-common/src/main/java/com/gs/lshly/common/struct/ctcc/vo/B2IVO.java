package com.gs.lshly.common.struct.ctcc.vo;
import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 电信类产品
 *
 * 
 * @author yingjun
 * @date 2021年4月20日 上午10:37:25
 */
@SuppressWarnings("serial")
public abstract class B2IVO implements Serializable {

	
	/**
	 * 电信国际
	 *
	 * 
	 * @author yingjun
	 * @date 2021年4月20日 上午10:41:48
	 */
	@Data
    @ApiModel("B2IVO.SimpleBusinessAcceptCreateVO")
    @Accessors(chain = true)
    public static class SimpleBusinessAcceptCreateVO implements Serializable{
    	
		/**
		 * {"success":true,"data":"B2IYWDD202006031454045853124"},"message":"success","code":"0000"}
		 */
		private Boolean success;
		private String data;
		private String message;
		private String code;
    }
	
}
