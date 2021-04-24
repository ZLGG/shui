package com.gs.lshly.common.struct.platadmin.commodity.qto;


import java.io.Serializable;

import com.gs.lshly.common.struct.BaseQTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * @author Hasee
 * @since 2020/09/17
 */
@SuppressWarnings("serial")
public abstract class GoodsBrandQTO implements Serializable  {

    @Data
    @ApiModel("GoodsBrandQTO.QTO")
    @EqualsAndHashCode(callSuper=false)
    public static class QTO extends BaseQTO {
        @ApiModelProperty(value = "品牌名称")
        private String brandName;

        @ApiModelProperty(value = "品牌别名")
        private String brandAlias;
    }
    
    
    @Data
    @ApiModel("GoodsBrandQTO.IdQTO")
    @EqualsAndHashCode(callSuper=false)
    public static class IdQTO extends BaseQTO {
        @ApiModelProperty(value = "品牌ID")
        private String id;
    }

}
