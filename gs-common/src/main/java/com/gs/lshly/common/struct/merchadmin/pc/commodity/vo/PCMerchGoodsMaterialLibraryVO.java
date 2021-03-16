package com.gs.lshly.common.struct.merchadmin.pc.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
* @author Starry
* @since 2020-12-10
*/
public abstract class PCMerchGoodsMaterialLibraryVO implements Serializable {

    @Data
    @ApiModel("PCMerchGoodsMaterialLibraryVO.ListVO")
    @Accessors(chain = true)
    public static class ListVO implements Serializable{

        @ApiModelProperty("商品素材库id")
        private String id;


        @ApiModelProperty("商品类目id")
        private String categoryId;


        @ApiModelProperty("商品品牌id")
        private String brandId;


        @ApiModelProperty("商品标题（商品名称）")
        private String goodsName;


        @ApiModelProperty("商品副标题")
        private String goodsTitle;


        @ApiModelProperty("商品移动端描述")
        private String goodsH5Desc;


        @ApiModelProperty("商品电脑端描述")
        private String goodsPcDesc;


        @ApiModelProperty("操作人")
        private String operator;




    }

    @Data
    @ApiModel("PCMerchGoodsMaterialLibraryVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("类目名称")
        private String gsCategoryName;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("图片列表")
        private List<String> imageList;

    }
}
