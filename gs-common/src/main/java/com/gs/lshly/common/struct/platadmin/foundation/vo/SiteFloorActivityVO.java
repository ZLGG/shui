package com.gs.lshly.common.struct.platadmin.foundation.vo;
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
public abstract class SiteFloorActivityVO implements Serializable {

    @Data
    @ApiModel("SiteFloorActivityVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("活动宣传图ID")
        private String id;


        @ApiModelProperty("宣传图")
        private String img;


        @ApiModelProperty("楼层名字")
        private String floorName;


        @ApiModelProperty("链接")
        private String link;




    }

    @Data
    @ApiModel("SiteFloorActivityVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
