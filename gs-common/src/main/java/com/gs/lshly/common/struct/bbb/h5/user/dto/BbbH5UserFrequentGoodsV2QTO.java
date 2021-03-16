package com.gs.lshly.common.struct.bbb.h5.user.dto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class BbbH5UserFrequentGoodsV2QTO implements Serializable {

    @Data
    @ApiModel("BbbH5UserFrequentGoodsV2QTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
