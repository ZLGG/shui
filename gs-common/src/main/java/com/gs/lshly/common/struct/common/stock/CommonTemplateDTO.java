package com.gs.lshly.common.struct.common.stock;

import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public abstract class CommonTemplateDTO implements Serializable {

    @Data
    @ApiModel("CommonTemplateDTO.AddETO")
    @Accessors(chain = true)
    public static class AddETO extends BaseDTO {

        @ApiModelProperty(value = "运费模板名称",position = 1)
        private String templateName;

        @ApiModelProperty(value = "运费区间",position = 2)
        private List<PriceDTO> priceDTOList;
    }

    @Data
    @ApiModel("CommonTemplateDTO.UpdateETO")
    @Accessors(chain = true)
    public static class UpdateETO extends BaseDTO {

        @ApiModelProperty(value = "运费模板id",position = 1)
        private String id;

        @ApiModelProperty(value = "运费模板名称",position = 2)
        private String templateName;

        @ApiModelProperty(value = "运费区间",position = 3)
        private List<PriceDTO> priceDTOList;
    }

    @Data
    @ApiModel("CommonTemplateDTO.PriceDTO")
    @Accessors(chain = true)
    public static class PriceDTO extends BaseDTO {

        @ApiModelProperty(value = "地区",position = 1,example = "浙江省,上海市")
        private String address;

        @ApiModelProperty(value = "运费金额",position = 2)
        private BigDecimal price;

        @ApiModelProperty(value = "运费区间",position = 3)
        private List<RegionDTO> regionDTOList;
    }

    @Data
    @ApiModel("CommonTemplateDTO.RegionDTO")
    @Accessors(chain = true)
    public static class RegionDTO extends BaseDTO {

        @ApiModelProperty(value = "地区id",position = 2)
        private String regionId;

        @ApiModelProperty(value = "地区名",position = 3)
        private String regionName;

        @ApiModelProperty(value = "地区层级 10=省 20=市 30=县",position = 4)
        private Integer regionLevel;
    }
}
