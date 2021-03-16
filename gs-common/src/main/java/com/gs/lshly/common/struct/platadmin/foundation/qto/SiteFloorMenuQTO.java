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
public abstract class SiteFloorMenuQTO implements Serializable {

    @Data
    @ApiModel("SiteFloorMenuQTO.QTO")
    @Accessors(chain = true)
    public static class QTO extends BaseQTO {

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty(value = "菜单类型[10=楼层顶部 20=左侧链接]",hidden = true)
        private Integer menuType;

    }

    @Data
    @ApiModel("SiteFloorMenuQTO.SelectByConQTO")
    @Accessors(chain = true)
    @AllArgsConstructor
    public static class IdAndTypeQTO extends BaseQTO {

        @ApiModelProperty("楼层id")
        private String floorId;

        @ApiModelProperty("菜单类型[10=楼层顶部 20=左侧链接]")
        private Integer menuType;


    }
}
