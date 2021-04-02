package com.gs.lshly.common.struct.bbb.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2021-03-09
*/
public abstract class PCBbbMerchantSiteNavigationVO implements Serializable {

    @Data
    @ApiModel("PCBbbMerchantSiteNavigationVO.ListVO")
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
    @ApiModel("PCBbbMerchantSiteNavigationVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
