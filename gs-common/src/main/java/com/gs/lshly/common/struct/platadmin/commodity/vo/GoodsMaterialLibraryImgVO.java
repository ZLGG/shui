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
* @since 2020-12-10
*/
public abstract class GoodsMaterialLibraryImgVO implements Serializable {

    @Data
    @ApiModel("GoodsMaterialLibraryImgVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品素材库图片id")
        private String id;


        @ApiModelProperty("素材库id")
        private String materialLibraryId;


        @ApiModelProperty("图片路径")
        private String imageUrl;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("GoodsMaterialLibraryImgVO.DetailVO")
    public static class DetailVO extends ListVO {

    }
}
