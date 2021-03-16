package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author 陈奇
* @since 2020-10-17
*/
public abstract class DataArticleCategoryDTO implements Serializable {

    @Data
    @ApiModel("DataArticleCategoryDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("终端[10=2b 20=2c]")
        private Integer terminal;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault = TrueFalseEnum.否.getCode();

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty("是否第一层级[10=是  20=否]")
        private Integer leveone;
    }

    @Data
    @ApiModel("DataArticleCategoryDTO.ADTO")
    @Accessors(chain = true)
    public static class ADTO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("栏目图标")
        private String icon;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault = TrueFalseEnum.否.getCode();

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty(value = "是否第一层级[10=是  20=否]",hidden = true)
        private Integer leveone;

    }

    @Data
    @ApiModel("DataArticleCategoryDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("DataArticleCategoryDTO.UDTO")
    @Accessors(chain = true)
    public static class UDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("栏目图标")
        private String icon;

        @ApiModelProperty("栏目名称")
        private String name;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault = TrueFalseEnum.否.getCode();

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("父节点")
        private String perentId;

        @ApiModelProperty(value = "是否第一层级[10=是  20=否]",hidden = true)
        private Integer leveone;
    }

    @Data
    @ApiModel("DataArticleCategoryDTO.IdxDTO")
    @Accessors(chain = true)
    public static class IdxDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;

        @ApiModelProperty("排序")
        private Integer idx;

    }


}
