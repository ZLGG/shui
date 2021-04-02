package com.gs.lshly.common.struct.platadmin.foundation.dto;
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
* @since 2021-03-20
*/
public abstract class SiteFloorActivityDTO implements Serializable {

    @Data
    @ApiModel("SiteFloorActivityDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "活动宣传图ID",hidden = true)
        private String id;

        @ApiModelProperty("宣传图")
        private String img;

        @ApiModelProperty("楼层名字")
        private String floorName;

        @ApiModelProperty("链接")
        private String link;
    }

    @Data
    @ApiModel("SiteFloorActivityDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动宣传图ID")
        private String id;
    }


}
