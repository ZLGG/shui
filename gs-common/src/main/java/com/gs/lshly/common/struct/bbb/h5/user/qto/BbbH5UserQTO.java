package com.gs.lshly.common.struct.bbb.h5.user.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author xxfc
* @since 2020-10-27
*/
public abstract class BbbH5UserQTO implements Serializable {

    @Data
    @ApiModel("BbcUserQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

    }


    @Data
    @ApiModel("BbcUserQTO.ShoppingQTO")
    @Accessors(chain = true)
    public static class ShopQTO extends BaseDTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

    @Data
    @ApiModel("BbcUserQTO.GoodsQTO")
    @Accessors(chain = true)
    public static class GoodsQTO extends BaseQTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

}
