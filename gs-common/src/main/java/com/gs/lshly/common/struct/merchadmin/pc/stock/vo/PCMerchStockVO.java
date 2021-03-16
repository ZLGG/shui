package com.gs.lshly.common.struct.merchadmin.pc.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author zzg
* @since 2020-10-22
*/
public abstract class PCMerchStockVO implements Serializable {


    @Data
    @ApiModel("PCMerchStockVO.StockAlarmGoodsIdListVO")
    public static class StockAlarmGoodsIdListVO implements Serializable {
        private List<String> goodsIdList;
    }


    @Data
    @ApiModel("PCMerchStockVO.InnerRoliceVO")
    public static class InnerRoliceVO implements Serializable {

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("库存数量")
        private Integer quantity;

    }

}
