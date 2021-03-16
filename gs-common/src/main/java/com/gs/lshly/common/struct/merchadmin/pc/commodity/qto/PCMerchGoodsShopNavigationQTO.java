package com.gs.lshly.common.struct.merchadmin.pc.commodity.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-11-09
*/
public abstract class PCMerchGoodsShopNavigationQTO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsShopNavigationQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("店铺导航分类ID")
        private List<String> shopNavigation;

    }
}
