package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 大飞船
* @since 2020-09-29
*/
public abstract class SiteBannerQTO implements Serializable {

    @Data
    @ApiModel("SiteBannerQTO.H5QTO")
    @Accessors(chain = true)
    public static class H5QTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden=true)
        private Integer subject;
    }

    @Data
    @ApiModel("SiteBannerQTO.PCQTO")
    @Accessors(chain = true)
    public static class PCQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

}
