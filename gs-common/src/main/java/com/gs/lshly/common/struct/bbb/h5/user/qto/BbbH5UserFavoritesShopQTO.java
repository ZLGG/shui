package com.gs.lshly.common.struct.bbb.h5.user.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbbH5UserFavoritesShopQTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

    }
}
