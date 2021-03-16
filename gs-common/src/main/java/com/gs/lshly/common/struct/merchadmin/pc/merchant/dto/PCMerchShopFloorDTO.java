package com.gs.lshly.common.struct.merchadmin.pc.merchant.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @author Starry
* @since 2020-11-04
*/
public abstract class PCMerchShopFloorDTO implements Serializable {

    @Data
    @ApiModel("PCMerchShopFloorDTO.H5ETO")
    @Accessors(chain = true)
    public static class H5ETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("楼层数组")
        private List<H5ItemETO> floorList;

        @ApiModelProperty("删除数据")
        private List<String> removeIdList;


    }

    @Data
    @ApiModel("PCMerchShopFloorDTO.H5ItemETO")
    @Accessors(chain = true)
    public static class H5ItemETO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最多显示数量")
        private Integer showNum;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否新增[0=否 1=是]")
        private Integer isNew;

        @ApiModelProperty("楼层商品ID")
        private List<String> goodsIdList;
    }


    @Data
    @ApiModel("PCMerchShopFloorDTO.PCETO")
    @Accessors(chain = true)
    public static class PCETO extends BaseDTO {

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("楼层数组")
        private List<PCItemETO> floorList;

    }

    @Data
    @ApiModel("PCMerchShopFloorDTO.PCItemETO")
    @Accessors(chain = true)
    public static class PCItemETO extends BaseDTO {

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品最多显示数量")
        private Integer showNum;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("楼层商品数组")
        private List<PCGoodsItem> goodsItemList;

    }

    @Data
    @ApiModel("PCMerchShopFloorDTO.PCGoodsItem")
    @Accessors(chain = true)
    public static class PCGoodsItem implements Serializable {

        @ApiModelProperty(value = "商品ID")
        private String goodsId;

        @ApiModelProperty(value = "排序")
        private Integer idx;

    }
}
