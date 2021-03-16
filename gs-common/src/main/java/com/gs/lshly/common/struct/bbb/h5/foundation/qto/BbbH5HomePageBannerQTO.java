package com.gs.lshly.common.struct.bbb.h5.foundation.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class BbbH5HomePageBannerQTO implements Serializable {

    @Data
    @ApiModel("BBC.SiteBannerQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

        @ApiModelProperty(value = "是否是分类[1=是 0=否]",hidden = true)
        private Integer isClassify;
    }

    @Data
    @ApiModel("BBC.SiteBannerQTO.CQTO")
    @Accessors(chain = true)
    public static class CQTO extends BaseQTO {

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "是否是分类[1=是 0=否]",hidden = true)
        private Integer isClassify;

    }
}
