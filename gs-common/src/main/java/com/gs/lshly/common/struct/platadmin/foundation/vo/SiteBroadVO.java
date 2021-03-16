package com.gs.lshly.common.struct.platadmin.foundation.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-03
*/
public abstract class SiteBroadVO implements Serializable {

    @Data
    @ApiModel("SiteBroadVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;


        @ApiModelProperty("文本内容")
        private String text;


        @ApiModelProperty("跳转地址")
        private String linkUrl;


        @ApiModelProperty("排序")
        private Integer idx;




    }

    @Data
    @ApiModel("SiteBroadVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
