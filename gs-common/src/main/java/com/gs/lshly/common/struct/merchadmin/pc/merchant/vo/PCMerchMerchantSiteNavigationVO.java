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
* @since 2021-03-08
*/
public abstract class PCMerchMerchantSiteNavigationVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantSiteNavigationVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("主键id")
        private String id;


        @ApiModelProperty("名称")
        private String name;


        @ApiModelProperty("跳转地址")
        private String jumpUrl;


        @ApiModelProperty("排序")
        private Integer idx;




    }

    @Data
    @ApiModel("PCMerchMerchantSiteNavigationVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
