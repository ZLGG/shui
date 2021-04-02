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
* @since 2021-03-30
*/
public abstract class SiteActiveVO implements Serializable {

    @Data
    @ApiModel("SiteActiveVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("活动配置主键id")
        private String id;


        @ApiModelProperty("楼层名称")
        private String floorName;


        @ApiModelProperty("图片地址")
        private String imgUrl;


        @ApiModelProperty("图片跳转地址")
        private String imgSkipUrl;


        @ApiModelProperty("10=2b 20=2c")
        private Integer terminal;


        @ApiModelProperty("10=pc 20=h5")
        private Integer pcShow;




    }

    @Data
    @ApiModel("SiteActiveVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
