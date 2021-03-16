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
* @since 2020-10-27
*/
public abstract class GoodsCategoryAttributeVO implements Serializable {

    @Data
    @ApiModel("GoodsCategoryAttributeVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("")
        private String id;


        @ApiModelProperty("属性id")
        private String attributeId;


        @ApiModelProperty("类别id")
        private String categoryId;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("GoodsCategoryAttributeVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
