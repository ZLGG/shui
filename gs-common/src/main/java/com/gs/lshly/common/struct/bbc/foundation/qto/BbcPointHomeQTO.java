package com.gs.lshly.common.struct.bbc.foundation.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;

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

    /**
     * 猜你喜欢查询
     *
     * 
     * @author yingjun
     * @date 2021年4月13日 下午1:51:52
     */
    @Data
    @ApiModel("BbcPointHomeQTO.EnjoyQTO")
    @Accessors(chain = true)
    public static class EnjoyQTO extends BaseQTO {
    	@ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50积分商城]",hidden=true)
        private Integer subject;
    	
    	@ApiModelProperty(value="10 20",hidden=true)
        private Integer terminal;
    	
    	@ApiModelProperty(value="10猜你喜欢20 30 ...")
    	private Integer type;
    }
}
