package com.gs.lshly.common.struct.platadmin.foundation.vo;
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
public abstract class SiteFloorNewVO implements Serializable {

    @Data
    @ApiModel("SiteFloorNewVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("楼层配置id")
        private String id;


        @ApiModelProperty("楼层配置名称")
        private String floorName;


        @ApiModelProperty("楼层配置名称别名")
        private String floorAlias;


        @ApiModelProperty("是否在PC端显示(10=pc 20=h5)")
        private Integer pcShow;


        @ApiModelProperty("展示终端(10=2b 20=2c)")
        private Integer terminal;


        @ApiModelProperty("排序")
        private Integer idx;




    }

    @Data
    @ApiModel("SiteFloorNewVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
