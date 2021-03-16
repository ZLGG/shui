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
* @since 2020-11-11
*/
public abstract class SiteVO implements Serializable {

    @Data
    @ApiModel("SiteVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("商家登录背景图")
        private String merchantLoginBackimage;

        @ApiModelProperty("是否启用手机号验证[10=开启  20=关闭]")
        private Integer state;
    }

    @Data
    @ApiModel("SiteVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
