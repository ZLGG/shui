package com.gs.lshly.common.struct.platadmin.commodity.dto;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-10-15
*/
public abstract class GoodsLabelDTO implements Serializable {

    @Data
    @ApiModel("GoodsLabelDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "标签id",hidden = true)
        private String id;

        @ApiModelProperty("标签名称")
        private String labelName;

        @ApiModelProperty("标签备注")
        private String labelRemark;

        @ApiModelProperty("标签颜色")
        private String labelColor;

        @ApiModelProperty("标签类型")
        private Integer labelType;

    }

    @Data
    @ApiModel("GoodsLabelDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "标签id")
        private String id;
    }

    @Data
    @ApiModel("GoodsLabelDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "标签id列表")
        private List<String> idList;
    }


}
