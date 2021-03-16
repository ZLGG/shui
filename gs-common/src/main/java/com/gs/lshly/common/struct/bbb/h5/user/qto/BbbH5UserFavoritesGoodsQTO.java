package com.gs.lshly.common.struct.bbb.h5.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbH5UserFavoritesGoodsQTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
