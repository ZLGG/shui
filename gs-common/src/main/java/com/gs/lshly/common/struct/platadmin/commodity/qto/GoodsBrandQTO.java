package com.gs.lshly.common.struct.platadmin.commodity.qto;


import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * @author Hasee
 * @since 2020/09/17
 */
public abstract class GoodsBrandQTO implements Serializable  {

    @Data
    @ApiModel("GoodsBrandQTO.QTO")
    public static class QTO extends BaseQTO {
        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "品牌别名")
        private String brandAlias;
    }

}
