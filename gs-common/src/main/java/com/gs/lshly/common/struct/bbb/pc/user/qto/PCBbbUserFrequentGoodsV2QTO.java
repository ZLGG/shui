package com.gs.lshly.common.struct.bbb.pc.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class PCBbbUserFrequentGoodsV2QTO implements Serializable {

    @Data
    @ApiModel("PCBbbUserFrequentGoodsV2QTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
