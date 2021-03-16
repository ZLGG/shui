package com.gs.lshly.common.struct.bbb.h5.merchant.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-13
*/
public abstract class BbbH5ShopQTO implements Serializable {

    @Data
    @ApiModel("BbbH5ShopQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺名称")
        private String shopName;

    }

    @Data
    @ApiModel("BbbH5ShopQTO.FloorGoodsQTO")
    @Accessors(chain = true)
    public static class FloorGoodsQTO extends BaseQTO {

        @ApiModelProperty("楼层ID")
        private String floorId;

    }
    
    @Data
    @ApiModel("BbcShopQTO.InnerShopQTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class InnerShopQTO extends BaseDTO {

        @ApiModelProperty("店铺ID")
        private String shopId;

    }

    @Data
    @ApiModel("BbcShopQTO.InnerListShopQTO")
    @AllArgsConstructor
    public static class InnerListShopQTO extends BaseDTO {

        @ApiModelProperty("店铺ID")
        private List<String> shopIdList;
    }


}
