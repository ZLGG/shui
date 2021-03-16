package com.gs.lshly.common.struct.bbb.pc.user.vo;

import com.gs.lshly.common.struct.bbb.pc.commodity.vo.PCBbbGoodsInfoVO;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbUserFavoritesGoodsVO implements Serializable {

    @Data
    @ApiModel("BbbUserFavoritesGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO extends PCBbbGoodsInfoVO.HomeInnerServiceVO{

        @ApiModelProperty("商品id")
        private String goodsId;

    }

}
