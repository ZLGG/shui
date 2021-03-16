package com.gs.lshly.common.struct.platadmin.foundation.qto;
import com.gs.lshly.common.struct.BaseQTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

/**
* @author 大飞船
* @since 2020-09-29
*/
public abstract class SiteFloorGoodsQTO implements Serializable {

    @Data
    @ApiModel("SiteFloorGoodsQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("楼层ID")
        private String floorId;

    }
}
