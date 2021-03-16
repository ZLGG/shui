package com.gs.lshly.common.struct.common;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
* @author xxfc
* @since 2020-10-09
*/
public abstract class CorpCertDictVO implements Serializable {

    @Data
    @ApiModel("CorpCertDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("id")
        private String id;

        @ApiModelProperty("证照名称")
        private String certName;

        @ApiModelProperty("是否必需上传[0=否 1=是]")
        private Integer isNeed;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("最后修改时间")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;
    }

    @Data
    @ApiModel("CorpCertDictVO.DetailVO")
    public static class DetailVO extends ListVO {

    }

    @Data
    @ApiModel("CorpCertDictVO.SimpleVO")
    public static class SimpleVO implements Serializable{

        @ApiModelProperty("证照ID")
        private String id;

        @ApiModelProperty("证照名称")
        private String certName;

        @ApiModelProperty("是否必需上传[0=否 1=是]")
        private Integer isNeed;
    }
}
