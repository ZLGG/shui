package com.gs.lshly.common.struct.common;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author xxfc
* @since 2020-10-09
*/
public abstract class CorpTypeDictVO implements Serializable {

    @Data
    @ApiModel("CorpTypeDictVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty(value = "id",position = 1)
        private String id;

        @ApiModelProperty(value = "企业分类[10=买家 20=卖家]",position = 2)
        private Integer typeGroup;

        @ApiModelProperty(value = "企业类型名称",position = 3)
        private String typeName;

        @ApiModelProperty(value = "证照数组",position = 4)
        private List<CertItem> certList;

        @ApiModelProperty(value = "排序",position = 5)
        private Integer idx;

        @ApiModelProperty(value = "创建时间",position = 6)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime cdate;

        @ApiModelProperty(value = "最后修改时间",position = 7)
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        private LocalDateTime udate;


    }

    @Data
    @ApiModel("CorpTypeDictVO.DetailVO")
    public static class DetailVO implements Serializable {

        @ApiModelProperty("企业类型")
        private String id;

        @ApiModelProperty("企业分类[10=买家 20=卖家]")
        private Integer typeGroup;

        @ApiModelProperty("企业类型名称")
        private String typeName;

        @ApiModelProperty("排序")
        private Integer idx;

        @ApiModelProperty("证照数组")
        private List<CertItem> certList;

    }

    @Data
    @ApiModel("CorpTypeDictVO.CertItem")
    public static class CertItem implements Serializable{

        @ApiModelProperty("证照ID")
        private String id;

        @ApiModelProperty("证照名称")
        private String certName;

        @ApiModelProperty("是否必需上传[0=否 1=是]")
        private Integer isNeed;

    }

    @Data
    @ApiModel("CorpTypeDictVO.SimpleList")
    public static class SimpleList implements Serializable{

        @ApiModelProperty("企业类型")
        private String id;

        @ApiModelProperty("企业类型名称")
        private String typeName;
    }

}
