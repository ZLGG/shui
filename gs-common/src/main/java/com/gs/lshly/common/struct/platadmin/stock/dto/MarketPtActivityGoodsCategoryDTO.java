package com.gs.lshly.common.struct.platadmin.stock.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class MarketPtActivityGoodsCategoryDTO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityGoodsCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("活动id")
        private String activityId;

        @ApiModelProperty("商品类目id")
        private String categoryId;
    }

    @Data
    @ApiModel("MarketPtActivityGoodsCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "categoryId")
        private String categoryId;
    }


}
