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
public abstract class SiteNavigationQTO implements Serializable {

    @Data
    @ApiModel("SiteNavigationQTO.H5QTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public class H5QTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

    }

    @Data
    @ApiModel("SiteNavigationQTO.PCQTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public class PCQTO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "导航类型[10=顶部链接 20=菜单导航]",hidden = true)
        private Integer type;

    }
}
