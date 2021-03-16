package com.gs.lshly.common.struct.platadmin.stock.qto;
import com.gs.lshly.common.struct.BaseQTO;
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
public abstract class MarketPtActivityGoodsCategoryQTO implements Serializable {

    @Data
    @ApiModel("MarketPtActivityGoodsCategoryQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("活动id")
        private String activityId;

        @ApiModelProperty("商品类目id")
        private String categoryId;
    }
}
