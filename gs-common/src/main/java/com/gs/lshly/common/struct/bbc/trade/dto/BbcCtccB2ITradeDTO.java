package com.gs.lshly.common.struct.bbc.trade.dto;

import java.io.Serializable;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * B2I交易平台
 *
 * 
 * @author yingjun
 * @date 2021年5月28日 下午2:46:37
 */
@SuppressWarnings("serial")
public abstract class BbcCtccB2ITradeDTO implements Serializable {

	/**
	 * B2I简单加包业务受理接口DTO
	 *
	 * 
	 * @author yingjun
	 * @date 2021年5月28日 下午2:47:46
	 */
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel(value = "BbcCtccB2ITradeDTO.CreateSimpleBusinessAcceptDTO")
    @Accessors(chain = true)
    public static class CreateSimpleBusinessAcceptDTO  extends BaseDTO implements Serializable {

        @ApiModelProperty(value = "渠道方订单流水")
        private String outOrderSeq;

        @ApiModelProperty(value = "渠道名")
        private Long channelName;

        @ApiModelProperty(value = "渠道编码")
        private String channelCode;

        @ApiModelProperty(value="营销渠道名")
        private String yxqdName;
        
        @ApiModelProperty(value="营销渠道编码")
        private String yxqdCode;
        
//        @ApiModelProperty(value="第一发展人")
//        private String firstDeveloper;
        
        @ApiModelProperty(value="地市")
        private String c3Name;
        
        @ApiModelProperty(value="县市")
        private String c4Name;
        
        @ApiModelProperty(value="办理号码")
        private String codeNumber;
        
        @ApiModelProperty(value="资产类型(手机，带宽，固话，天翼高清)")
        private String assetsType;
        
        @ApiModelProperty(value="联系人")
        private String linkMan;
        
        @ApiModelProperty(value="联系号码")
        private String linkPhone;
        
        @ApiModelProperty(value="业务订单类型")
        private String gdType ="standard";
        
        @ApiModelProperty(value="人工介入(Y,N)")
        private String rgFlag;
        
        @ApiModelProperty(value="受理工号(渠道如果使用自己的工号，填写3.0受理工号)")
        private String slgh;
        
        @ApiModelProperty(value="备注")
        private String remark1;
        
        @ApiModelProperty(value="优惠信息列表(不超过三条优惠)")
        private List<DisCountListDTO> disCountList;
        
        @ApiModelProperty(value="优惠类型需要属性时必填，格式:JSONArray字符串")
        private List<Remark3DTO> remark3;
        
    }
	
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel(value = "BbcCtccB2ITradeDTO.DisCountListDTO")
    @Accessors(chain = true)
    public static class DisCountListDTO  extends BaseDTO implements Serializable {
		
		@ApiModelProperty(value="优惠编码")
        private String discountCode;
        
        @ApiModelProperty(value="优惠名称")
        private String discountName;
        
        @ApiModelProperty(value="优惠类型(促销,可选包,套餐销售品,基础销售品,子产品,主销售品)")
        private String disCountType;
		
	}

	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel(value = "BbcCtccB2ITradeDTO.Remark3DTO")
    @Accessors(chain = true)
    public static class Remark3DTO  extends BaseDTO implements Serializable {
		
		@ApiModelProperty(value="优惠编码")
        private String discountCode;
        
        @ApiModelProperty(value="属性编码:")
        private String attrId;
        
        @ApiModelProperty(value="属性值")
        private String attrValue;
		
	}
		
}
