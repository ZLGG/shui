package com.gs.lshly.common.struct.platadmin.commodity.dto;
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
* @since 2020-10-27
*/
public abstract class GoodsCategoryAttributeDTO implements Serializable {

    @Data
    @ApiModel("GoodsCategoryAttributeDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "ID",hidden = true)
        private String id;

        @ApiModelProperty("属性id")
        private String attributeId;

        @ApiModelProperty("类别id")
        private String categoryId;

    }

    @Data
    @ApiModel("GoodsCategoryAttributeDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {
        private String id;
    }


}
