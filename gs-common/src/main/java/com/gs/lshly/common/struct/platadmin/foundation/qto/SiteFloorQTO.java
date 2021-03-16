package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import com.gs.lshly.common.struct.platadmin.foundation.vo.SiteFloorVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-09-30
*/
public abstract class SiteFloorQTO implements Serializable {

    @Data
    @ApiModel("SiteFloorQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "是否PC显示[10=是 20=否]",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "终端[10=默认 20=扶贫  30=好粮油 40=推荐专栏 50积分专栏]",hidden=true)
        private Integer subject;
    }


    @Data
    @ApiModel("SiteFloorQTO.H5QTO")
    @Accessors(chain = true)
    public static class H5QTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }

    @Data
    @ApiModel("SiteFloorQTO.PCQTO")
    @Accessors(chain = true)
    public static class PCQTO extends BaseDTO {

        @ApiModelProperty(value = "专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;
    }


}
