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
public abstract class UserLeveDictDTO implements Serializable {

    @Data
    @ApiModel("UserLeveDictDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("等级名称")
        private String name;

        @ApiModelProperty("等级所需成长值")
        private Integer power;

        @ApiModelProperty("等级图标")
        private String icon;

        @ApiModelProperty("等级适用店铺类型[10=2c 20=2b]")
        private Integer leveType;
    }

    @Data
    @ApiModel("UserLeveDictDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("UserLeveDictDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {
        @ApiModelProperty(value = "ID数组")
        private List<String> idList;
    }

    @Data
    @ApiModel("UserLeveDictDTO.UsingTypeDTO")
    public static class UsingTypeDTO extends BaseDTO {

        @ApiModelProperty("等级适用店铺类型[10=2c 20=2b]")
        private Integer leveType;
    }
}
