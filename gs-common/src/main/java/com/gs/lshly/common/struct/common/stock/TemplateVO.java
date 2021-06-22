package com.gs.lshly.common.struct.common.stock;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public abstract class TemplateVO implements Serializable {

    @Data
    @ApiModel("TemplateVO.DetailVO")
    @Accessors(chain = true)
    public static class DetailVO  implements  Serializable {

        @ApiModelProperty(value = "运费模版ID",position = 2)
        private String id;

        @ApiModelProperty(value = "运费模板名称",position = 3)
        private String templateName;

        @ApiModelProperty(value = "运费区间",position = 4)
        private List<CommonTemplateDTO.PriceDTO> priceDTOList;
    }

    @Data
    @ApiModel("TemplateVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable {

        @ApiModelProperty(value = "运费模版ID",position = 2)
        private String id;

        @ApiModelProperty(value = "运费模板名称",position = 3)
        private String templateName;

        @ApiModelProperty(value = "运费区间",position = 4)
        private List<CommonTemplateDTO.PriceDTO> priceDTOList;

        @ApiModelProperty(value = "最后修改时间",position = 5, example = "2020-11-15 18:02:16")
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;
    }
}
