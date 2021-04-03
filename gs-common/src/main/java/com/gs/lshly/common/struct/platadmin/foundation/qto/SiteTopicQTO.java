package com.gs.lshly.common.struct.platadmin.foundation.qto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:29:25
 */
public abstract class SiteTopicQTO implements Serializable {

    @Data
    @ApiModel("SiteTopicQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    	@ApiModelProperty(value = "终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
    	
    	@ApiModelProperty(value = "终端[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50积分专栏]",hidden=true)
        private Integer subject;
    	
    	@ApiModelProperty(value = "专题名称",required=false)
        private String name;
    }

}
