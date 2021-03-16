package com.gs.lshly.common.struct.bbc.merchant.vo;
import com.gs.lshly.common.struct.bbc.commodity.vo.BbcGoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-11-05
*/
public abstract class BbcShopFloorVO implements Serializable {

    @Data
    @ApiModel("BbcShopFloorVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("楼层ID")
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("商品信息数组")
        private List<BbcGoodsInfoVO.HomeAndShopInnerServiceVO> goodsList;
    }
}
