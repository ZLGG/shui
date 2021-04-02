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
* @author Starry
* @since 2021-03-10
*/
public abstract class SiteFloorNewDTO implements Serializable {

    @Data
    @ApiModel("SiteFloorNewDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "楼层配置id",hidden = true)
        private String id;

        @ApiModelProperty("楼层配置名称")
        private String floorName;

        @ApiModelProperty("楼层配置名称别名")
        private String floorAlias;

        @ApiModelProperty(value = "是否在PC端显示(10=pc 20=h5)",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "展示终端(10=2b 20=2c)",hidden = true)
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("SiteFloorNewDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "楼层配置id")
        private String id;
    }

    @Data
    @ApiModel("SiteFloorNewDTO.ShowDTO")
    public static class ShowDTO extends BaseDTO {

        @ApiModelProperty(value = "是否在PC端显示(10=pc 20=h5)",hidden = true)
        private Integer pcShow;

        @ApiModelProperty(value = "展示终端(10=2b 20=2c)",hidden = true)
        private Integer terminal;
    }

}
