package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-09
*/
public abstract class PCMerchGoodsExtendParamsQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsExtendParamsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("参数组名")
        private String paramName;

        @ApiModelProperty("参数值")
        private String paramValue;

    }
}
