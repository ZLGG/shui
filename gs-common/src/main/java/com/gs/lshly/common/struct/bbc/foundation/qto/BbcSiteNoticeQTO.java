package com.gs.lshly.common.struct.bbc.foundation.qto;

import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;
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
 * @date 2021年3月11日 下午4:31:57
 */
public abstract class BbcSiteNoticeQTO implements Serializable {


    @Data
    @ApiModel("BbcSiteNoticeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO  extends BaseQTO{

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50=积分商城]",hidden = true)
        private Integer subject;

    }

    
    @Data
    @ApiModel("BbcSiteNoticeQTO.IDQTO")
    @Accessors(chain = true)
    public static class IDQTO  extends BaseDTO{

        @ApiModelProperty(value = "ID")
        private String id;

    }
}
