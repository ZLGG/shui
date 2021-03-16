package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
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
public abstract class SiteFloorMenuGoodsDTO implements Serializable {

    @Data
    @ApiModel("SiteFloorMenuGoodsDTO.ETO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("商品ID")
        private String goodsId;

        @ApiModelProperty("楼层菜单ID")
        private String floorMenuId;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorMenuGoodsDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }
}
