package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-30
*/
public abstract class PCMerchShopCustomAreaVO implements Serializable {

    @Data
    @ApiModel("PCMerchShopCustomAreaVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("自定义区域内容")
        private String content;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("PCMerchShopCustomAreaVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
