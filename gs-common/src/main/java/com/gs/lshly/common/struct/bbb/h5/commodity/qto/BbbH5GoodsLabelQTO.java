package com.gs.lshly.common.struct.bbb.h5.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author Starry
* @since 2020-11-11
*/
public abstract class BbbH5GoodsLabelQTO implements Serializable {

    @Data
    @ApiModel("BbcGoodsLabelQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {


    }
}
