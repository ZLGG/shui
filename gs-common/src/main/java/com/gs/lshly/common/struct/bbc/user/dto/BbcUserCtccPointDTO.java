package com.gs.lshly.common.struct.bbc.user.dto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年5月19日 上午10:18:32
 */
@SuppressWarnings("serial")
public abstract class BbcUserCtccPointDTO implements Serializable {
	
    @EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcUserCtccPointDTO.SubCtccPointDTO")
    @AllArgsConstructor
    public static class SubCtccPointDTO extends BaseDTO {
    	@ApiModelProperty("会员ID")
        private String userId;

    	@ApiModelProperty("扣减的积分值")
        private Integer point;
    }

    
    @EqualsAndHashCode(callSuper=false)
	@Data
    @ApiModel("BbcUserCtccPointDTO.AddCtccPointDTO")
//    @AllArgsConstructor
    public static class AddCtccPointDTO extends BaseDTO {
    	@ApiModelProperty("会员ID")
        private String userId;

    	@ApiModelProperty("添加的积分值")
        private Integer point;
    }
}
