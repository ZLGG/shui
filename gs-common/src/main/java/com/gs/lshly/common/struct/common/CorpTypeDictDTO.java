package com.gs.lshly.common.struct.common;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-09
*/
public abstract class CorpTypeDictDTO implements Serializable {

    @Data
    @ApiModel("CorpTypeDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("企业类型名称")
        private String typeName;

        @ApiModelProperty("企业分类[10=买家 20=卖家]")
        private Integer typeGroup;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("证照ID数组")
        private List<String> certIdList;
    }

    @Data
    @ApiModel("CorpTypeDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("CorpTypeDictDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }
}
