package com.gs.lshly.common.struct.platadmin.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 14:25 2020/9/23
 */
public abstract class GoodsAttributeDictionaryItemQTO implements Serializable {

    @Data
    public static class QTO extends BaseQTO{
        @ApiModelProperty(value = "属性id")
        private String attributeId;
    }

}
