package com.gs.lshly.common.struct.bbb.h5.pages.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author hyy
* @since 2020-11-04
*/
public abstract class BbbH5WelfareQTO implements Serializable {

    @Data
    @ApiModel("BbbH5WelfareQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value="专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]",hidden = true)
        private Integer subject;

    }

    @Data
    @ApiModel("BbbH5WelfareQTO.FloorGoodsQTO")
    @Accessors(chain = true)
    public static class FloorGoodsQTO extends BaseQTO {

        @ApiModelProperty(value="楼层ID",hidden = true)
        private String floorId;

        @ApiModelProperty("楼层最大商品显示数量")
        private Integer goodsMax;

    }
}
