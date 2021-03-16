package com.gs.lshly.common.struct.bbb.h5.user.vo;
import com.gs.lshly.common.struct.bbb.h5.commodity.vo.BbbH5GoodsInfoVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
* @author xxfc
* @since 2020-12-10
*/
public abstract class BbbH5UserFrequentGoodsVO implements Serializable {

    @Data
    @ApiModel("BbbH5UserFrequentGoodsVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("常购ID")
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty(value = "常购商品数量")
        private Integer quantity;

        @ApiModelProperty("商品SKU信息")
        private List<BbbH5GoodsInfoVO.InnerServiceVO> goodsSkuList = new ArrayList<>();

    }
}
