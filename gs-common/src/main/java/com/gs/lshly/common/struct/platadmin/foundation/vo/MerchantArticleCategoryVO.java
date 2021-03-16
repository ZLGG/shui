package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-10-29
*/
public abstract class MerchantArticleCategoryVO implements Serializable {

    @Data
    @ApiModel("MerchantArticleCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("栏目名称")
        private String name;


        @ApiModelProperty("是否底部显示[10=是 20=否]")
        private Integer bottom;


        @ApiModelProperty("终端[10=2c 20=2b ]")
        private Integer terminal;


        @ApiModelProperty("排序")
        private Integer idx;


        @ApiModelProperty("层级[1-2]1级不关联文章")
        private Integer leve;


        @ApiModelProperty("父ID")
        private String parentId;


        @ApiModelProperty("店铺ID")
        private String shopId;


        @ApiModelProperty("商家ID")
        private String merchantId;




    }

    @Data
    @ApiModel("MerchantArticleCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
