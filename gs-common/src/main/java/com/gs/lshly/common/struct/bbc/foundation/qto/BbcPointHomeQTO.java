package com.gs.lshly.common.struct.bbc.foundation.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 首页QTO
 *
 * 
 * @author yingjun
 * @date 2021年4月12日 上午12:11:07
 */
public class BbcPointHomeQTO implements Serializable {

    @Data
    @ApiModel("BbcPointHomeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {
    	@ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50积分商城]",hidden=true)
        private Integer subject;
    	
    	@ApiModelProperty(value="10 20",hidden=true)
        private Integer terminal;
    }

}
