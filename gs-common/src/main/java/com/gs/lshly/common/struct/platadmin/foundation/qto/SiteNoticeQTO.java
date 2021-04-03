package com.gs.lshly.common.struct.platadmin.foundation.qto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 广告弹窗
 *
 * 
 * @author yingjun
 * @date 2021年3月10日 上午2:29:25
 */
public abstract class SiteNoticeQTO implements Serializable {

    @Data
    @ApiModel("SiteNoticeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    	@ApiModelProperty(value = "终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
    	
    	@ApiModelProperty(value = "公告标题")
        private String name;
    }

}
