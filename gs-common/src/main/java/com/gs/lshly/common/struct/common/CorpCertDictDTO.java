package com.gs.lshly.common.struct.common;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-09
*/
public abstract class CorpCertDictDTO implements Serializable {

    @Data
    @ApiModel("CorpCertDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("证照名称")
        private String certName;

        @ApiModelProperty("是否必需上传[0=否 1=是]")
        private Integer isNeed;

        @ApiModelProperty("排序")
        private Integer idx;
    }

    @Data
    @ApiModel("CorpCertDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("CorpCertDictDTO.IdListDTO")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "证照ID数组")
        private List<String> idList;
    }

}
