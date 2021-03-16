package com.gs.lshly.common.struct.bbb.pc.commodityfupin.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-12-09
*/
public abstract class PCBbbGoodsFupinVO implements Serializable {

    @Data
    @ApiModel("PCBbbGoodsFupinVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("扶贫商品id")
        private String id;


        @ApiModelProperty("商品id")
        private String goodsId;


        @ApiModelProperty("店铺id")
        private String shopId;


        @ApiModelProperty("商家id")
        private String merchantId;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PCBbbGoodsFupinVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("PCBbbGoodsFupinVO.GoodsIdVO")
    @Accessors(chain = true)
    public static class GoodsIdVO implements Serializable{
        @ApiModelProperty("商品id列表")
        private List<String> goodsId;
    }

}
