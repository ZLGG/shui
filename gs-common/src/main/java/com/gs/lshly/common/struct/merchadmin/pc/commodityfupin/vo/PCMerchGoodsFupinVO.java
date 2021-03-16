package com.gs.lshly.common.struct.merchadmin.pc.commodityfupin.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-12-08
*/
public abstract class PCMerchGoodsFupinVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsFupinVO.ListVO")
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
    @ApiModel("PCMerchGoodsFupinVO.DetailVO")
    public static class DetailVO extends ListVO {
        @ApiModelProperty("图片凭证")
        List<PCMerchGoodsFupinImageVO.ListVO> listVOS = new ArrayList<>();
    }
}
