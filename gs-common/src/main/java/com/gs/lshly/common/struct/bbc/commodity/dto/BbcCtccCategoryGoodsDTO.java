package com.gs.lshly.common.struct.bbc.commodity.dto;

import java.io.Serializable;

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
 * @date 2021年4月15日 上午10:51:19
 */
@SuppressWarnings("serial")
public abstract class BbcCtccCategoryGoodsDTO implements Serializable {

	@Data
    @ApiModel(value = "DTO")
    @Accessors(chain = true)
    public static class DTO  extends BaseDTO implements Serializable {

    	@ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分专栏   60=电信国际   70=电信产品]", hidden = true)
        private Integer subject;

        @ApiModelProperty(value = "10 20", hidden = true)
        private Integer terminal;
    }




}
