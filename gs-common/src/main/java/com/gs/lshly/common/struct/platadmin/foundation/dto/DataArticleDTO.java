package com.gs.lshly.common.struct.platadmin.foundation.dto;
import com.gs.lshly.common.enums.TrueFalseEnum;
import com.gs.lshly.common.struct.BaseDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.List;

/**
* @author 陈奇
* @since 2020-10-19
*/
public abstract class DataArticleDTO implements Serializable {

    @Data
    @ApiModel("DataArticleDTO.ETO")
    @Accessors(chain = true)
    public static class ETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("标题")
        private String title;

        @ApiModelProperty("是否PC显示[10=PC 20=H5  30=全部]")
        private Integer pcShow;

        @ApiModelProperty("logo")
        private String logo;

        @ApiModelProperty("内容")
        private String content;

        @ApiModelProperty("是否默认显示[0=否 1=是]")
        private Integer isDefault = TrueFalseEnum.否.getCode();

        @ApiModelProperty(value = "终端[10=2b 20=2c]",hidden = true)
        private Integer terminal;

        @ApiModelProperty("文章栏目ID数组")
        private List<String> categoryList;

        @ApiModelProperty("排序")
        private Integer idx;

    }


    @Data
    @ApiModel("DataArticleDTO.IdxETO")
    @Accessors(chain = true)
    public static class IdxETO extends BaseDTO {

        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty("排序")
        private Integer idx;

    }

    @Data
    @ApiModel("DataArticleDTO.IdDTO")
    @AllArgsConstructor
    public static class IdDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("DataArticleDTO.NameDTO")
    @Accessors(chain = true)
    public static class NameDTO extends BaseDTO {

        @ApiModelProperty(value = "id")
        private String id;
    }

    @Data
    @ApiModel("DataArticleDTO.IdListDTO")
    public static class IdListDTO extends BaseDTO {

        @ApiModelProperty(value = "文章ID数组")
        List<String> idList;
    }



}
