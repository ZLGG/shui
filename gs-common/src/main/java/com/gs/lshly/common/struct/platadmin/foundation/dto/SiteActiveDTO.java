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
* @since 2021-03-30
*/
public abstract class SiteActiveDTO implements Serializable {

    @Data
    @ApiModel("SiteActiveDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "活动配置主键id",hidden = true)
        private String id;

        @ApiModelProperty("楼层名称")
        private String floorName;

        @ApiModelProperty("图片地址")
        private String imgUrl;

        @ApiModelProperty("图片跳转地址")
        private String imgSkipUrl;

        @ApiModelProperty(value = "10=2b 20=2c",hidden = true)
        private Integer terminal;

        @ApiModelProperty(value = "10=pc 20=h5",hidden = true)
        private Integer pcShow;
    }

    @Data
    @ApiModel("SiteActiveDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "活动配置主键id")
        private String id;
    }

    @Data
    @ApiModel("SiteActiveDTO.QueryDTO")
    public static class QueryDTO extends BaseDTO {

        @ApiModelProperty("10=2b 20=2c")
        private Integer terminal;

        @ApiModelProperty("10=pc 20=h5")
        private Integer pcShow;
    }


}
