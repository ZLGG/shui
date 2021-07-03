package com.gs.lshly.common.struct.bbc.user.dto;

import java.io.Serializable;
import java.util.List;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.bbc.trade.vo.BbcCouponVO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年7月4日 上午12:08:22
 */
@SuppressWarnings("serial")
public class BbcInUserCouponDTO implements Serializable {
	@EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcInUserCouponDTO.CreateDTO")
    @Accessors(chain = true)
    public static class CreateDTO extends BaseDTO {
        @ApiModelProperty("手机号码")
        private String phone;
        
        @ApiModelProperty("IN会员优惠券类型")
        private Integer inCouponType;
        
        @ApiModelProperty("优惠券列表")
        private List<BbcCouponVO.InCouponVO> list;
    }

}
