package com.gs.lshly.common.struct.bbb.h5.user.vo;

import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbH5UserFavoritesGoodsVO implements Serializable {

    @Data
    @ApiModel("BbbH5UserFavoritesGoodsVO.HomeInnerServiceVO")
    @Accessors(chain = true)
    public static class ListVO extends BbbH5GoodsInfoVO.HomeInnerServiceVO{

    }

}
