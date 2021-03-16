package com.gs.lshly.common.struct.bbc.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author xxfc
* @since 2020-11-02
*/
public abstract class BbcSiteFloorDTO implements Serializable {

    @Data
    @ApiModel("BbcSiteFloorDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("楼层名")
        private String name;

        @ApiModelProperty("小图标")
        private String icon;

        @ApiModelProperty("左侧栏大图")
        private String leftImage;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("是否PC显示[10=是 20=否]")
        private Integer pcShow;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("专栏类型[10=默认 20=扶贫  30=好粮油 40=推荐专栏]")
        private Integer subject;
    }

    @Data
    @ApiModel("BbcSiteFloorDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
