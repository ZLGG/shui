package com.gs.lshly.common.struct.bbc.commodity.qto;

import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

public abstract class BbcGoodsServeQTO implements Serializable {
    @Data
    @ApiModel("BbcGoodsServeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {
        @ApiModelProperty("服务名称")
        private String serveName;

        @ApiModelProperty("服务信息")
        private String serveContext;
    }

    @Data
    @ApiModel("BbcGoodsServeQTO.GoodsInfoQTO")
    public static class GoodsInfoQTO extends BaseQTO {

        @ApiModelProperty("商品id")
        private String id;
    }
}
