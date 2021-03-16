package com.gs.lshly.common.struct.platadmin.user.dto;
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
* @since 2020-10-05
*/
public abstract class UserLabelDictDTO implements Serializable {

    @Data
    @ApiModel("UserLabelDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("标签名")
        private String labelName;

        @ApiModelProperty("颜色")
        private String labelColor;
    }

    @Data
    @ApiModel("UserLabelDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("UserLabelDictDTO.IdDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "ID数组")
        private List<String> idList;

    }

    @Data
    @ApiModel("UserLabelDictDTO.AddUserLabelDTO")
    public static class AddUserLabelDTO extends BaseDTO {

        @ApiModelProperty(value = "会员ID数组")
        private List<String> userIdList;

        @ApiModelProperty(value = "标签ID数组")
        private List<String> labelIdList;

    }
}
