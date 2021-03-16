package com.gs.lshly.common.struct.platadmin.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Starry
 * @Date 11:10 2020/9/25
 */
public abstract class GoodsSpecDictionaryItemQTO implements Serializable {

    @Data
    public static class QTO extends BaseQTO {
        @ApiModelProperty(value = "规格id")
        private String specId;
    }
}
