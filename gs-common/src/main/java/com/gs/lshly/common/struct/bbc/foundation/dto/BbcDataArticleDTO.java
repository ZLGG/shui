package com.gs.lshly.common.struct.bbc.foundation.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.sql.Blob;
import java.time.LocalDateTime;
/**
* @author hyy
* @since 2020-11-13
*/
public abstract class BbcDataArticleDTO implements Serializable {

    @Data
    @ApiModel("BbcDataArticleDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("内容")
        private Blob content;

        @ApiModelProperty("文章图标")
        private String logo;

        @ApiModelProperty("阅读量")
        private Integer readCount;

        @ApiModelProperty("是否PC显示[10=PC 20=H5  30=全部]")
        private Integer pcShow;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("发布时间")
        private LocalDateTime sendTime;
    }

    @Data
    @ApiModel("BbcDataArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }


}
