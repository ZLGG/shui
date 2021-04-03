package com.gs.lshly.common.struct.platadmin.foundation.qto;
import java.io.Serializable;

import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class SiteAdvertPopupQTO implements Serializable {

    @Data
    @ApiModel("SiteAdvertPopupQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
    	@ApiModelProperty(value = "终端[10=2b 20=2c]",hidden=true)
        private Integer terminal;
    }


    @Data
    @ApiModel("SiteAdvertPopupQTO.Editor")
    @Accessors(chain = true)
    public static class H5SubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]")
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertPopupQTO.PCSubjectQTO")
    @Accessors(chain = true)
    public static class PCSubjectQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteAdvertPopupQTO.PCBillBoardQTO")
    @Accessors(chain = true)
    public static class PCBillBoardQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    
    @Data
    @ApiModel("SiteAdvertPopupQTO.BBBPCQTO")
    @Accessors(chain = true)
    public static class BBBPCQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }
}
