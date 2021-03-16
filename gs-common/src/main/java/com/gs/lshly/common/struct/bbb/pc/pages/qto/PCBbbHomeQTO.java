package com.gs.lshly.common.struct.bbb.pc.pages.qto;

import com.gs.lshly.common.struct.BaseDTO;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
* @author 陈奇
* @since 2020-10-14
*/
public abstract class PCBbbHomeQTO implements Serializable {

    @Data
    @ApiModel("PCBbbHomeQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseDTO {

        @ApiModelProperty(value = "专栏",hidden = true)
        private Integer subject;

    }

    @Data
    @ApiModel("PCBbbHomeQTO.MenuGoodsQTO")
    @Accessors(chain = true)
    public static class MenuGoodsQTO extends BaseDTO {

        @ApiModelProperty("楼层菜单ID")
        private String floorMenuId;

    }

    @Data
    @ApiModel("PCBbbHomeQTO.ShopSearchQTO")
    @Accessors(chain = true)
    public static class ShopSearchQTO extends BaseQTO {

        @ApiModelProperty("搜索内容")
        private String content;

    }

}
