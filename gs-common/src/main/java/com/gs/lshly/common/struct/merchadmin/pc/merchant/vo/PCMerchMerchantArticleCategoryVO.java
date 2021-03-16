package com.gs.lshly.common.struct.merchadmin.pc.merchant.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-07
*/
public abstract class PCMerchMerchantArticleCategoryVO implements Serializable {

    @Data
    @ApiModel("PCMerchMerchantArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("更新时间")
        private LocalDateTime udate;

    }

    @Data
    @ApiModel("PCMerchMerchantArticleCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
