package com.gs.lshly.common.struct.platadmin.foundation.qto;
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
* @since 2021-03-20
*/
public abstract class SiteFloorActivityQTO implements Serializable {

    @Data
    @ApiModel("SiteFloorActivityQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("宣传图")
        private String img;

        @ApiModelProperty("楼层名字")
        private String floorName;

        @ApiModelProperty("链接")
        private String link;
    }
}
