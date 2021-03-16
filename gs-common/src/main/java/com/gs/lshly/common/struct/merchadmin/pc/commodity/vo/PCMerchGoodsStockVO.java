package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author Starry
* @since 2020-10-27
*/
public abstract class PCMerchGoodsStockVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsStockVO.SkuAlarmListVO")
    @Accessors(chain = true)
    public static class SkuAlarmListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;

        @ApiModelProperty("店铺id")
        private String shopId;

        @ApiModelProperty("商家id")
        private String merchantId;

        @ApiModelProperty("库存报警数")
        private Integer stockNum;

        @ApiModelProperty("操作人")
        private String operator;

    }

    @Data
    @ApiModel("PCMerchGoodsStockVO.SkuAlarmDetailVO")
    public static class SkuAlarmDetailVO extends SkuAlarmListVO {

    }
}
