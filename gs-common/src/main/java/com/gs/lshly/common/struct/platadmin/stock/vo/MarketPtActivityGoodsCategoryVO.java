package com.gs.lshly.common.struct.platadmin.stock.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author zdf
* @since 2020-11-28
*/
public abstract class MarketPtActivityGoodsCategoryVO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityGoodsCategoryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("活动id")
        private String activityId;


        @ApiModelProperty("商品类目id")
        private String categoryId;




    }

    @Data
    @ApiModel("MarketPtActivityGoodsCategoryVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
