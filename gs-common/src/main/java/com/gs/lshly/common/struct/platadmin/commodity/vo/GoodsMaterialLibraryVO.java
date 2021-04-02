package com.gs.lshly.common.struct.platadmin.commodity.vo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
* @author Starry
* @since 2020-12-10
*/
public abstract class GoodsMaterialLibraryVO implements Serializable {

    @Data
    @ApiModel("GoodsMaterialLibraryVO.ListVO")
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
    @ApiModel("GoodsMaterialLibraryVO.DetailListVO")
    public static class DetailListVO extends ListVO{
        @ApiModelProperty("类目名称")
        private String gsCategoryName;

        @ApiModelProperty("品牌名称")
        private String brandName;

        @ApiModelProperty("图片")
        private String imageUrl;
    }

    @Data
    @ApiModel("GoodsMaterialLibraryVO.exportDataVO")
    public static class exportDataVO implements Serializable{
        @ApiModelProperty(value = "id",hidden = true)
        private String id;

        @ApiModelProperty(value = "三级类目id",hidden = true)
        private String categoryId;

        @ApiModelProperty(value = "一级类目名称",position = 1)
        private String level1CategoryName;

        @ApiModelProperty(value = "二级类目名称",position = 2)
        private String level2CategoryName;

        @ApiModelProperty(value = "三级类目名称",position = 3)
        private String level3CategoryName;

        @ApiModelProperty(value = "品牌名称",position = 4)
        private String brandName;

        @ApiModelProperty(value = "商品标题（商品名称）",position = 5)
        private String goodsName;

        @ApiModelProperty(value = "商品副标题",position = 6)
        private String goodsTitle;
    }

    @Data
    @ApiModel("GoodsMaterialLibraryVO.DetailVO")
    public static class DetailVO extends ListVO {

        @ApiModelProperty("图片列表")
        private List<String> imageList = new ArrayList<>();
    }
}
