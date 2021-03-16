package com.gs.lshly.common.struct.platadmin.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
/**
* @author Starry
* @since 2020-10-15
*/
public abstract class GoodsLabelVO implements Serializable {

    @Data
    @ApiModel("GoodsLabelVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("标签id")
        private String id;


        @ApiModelProperty("标签名称")
        private String labelName;


        @ApiModelProperty("标签备注")
        private String labelRemark;


        @ApiModelProperty("标签颜色")
        private String labelColor;


        @ApiModelProperty("标签类型")
        private Integer labelType;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("GoodsLabelVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
