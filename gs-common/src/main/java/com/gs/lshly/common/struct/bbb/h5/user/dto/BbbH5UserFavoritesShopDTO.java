package com.gs.lshly.common.struct.bbb.h5.user.dto;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-24
*/
public abstract class BbbH5UserFavoritesShopDTO implements Serializable {

    @Data
    @ApiModel("BbcUserFavoritesShopDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("店铺ID")
        private String shopId;

    }


    @Data
    @ApiModel("BbcUserFavoritesShopDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "店铺ID数组")
        private List<String> idList;

    }

}
