package com.gs.lshly.common.struct.bbb.pc.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-28
*/
public abstract class BbbUserFavoritesGoodsDTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("店铺ID")
        private String shopId;
    }

    @Data
    @ApiModel("BbcUserFavoritesGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        @ApiModelProperty(value = "收藏ID")
        private String id;
    }

    @Data
    @ApiModel("BbcUserFavoritesGoodsDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "商品ID数组")
        private List<String> idList;
    }

}
